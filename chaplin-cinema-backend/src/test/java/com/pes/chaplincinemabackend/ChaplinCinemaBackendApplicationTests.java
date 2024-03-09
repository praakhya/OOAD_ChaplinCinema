package com.pes.chaplincinemabackend;

import com.beust.ah.A;
import com.pes.chaplincinemabackend.auth.entities.Role;
import com.pes.chaplincinemabackend.auth.pojos.User;
import com.pes.chaplincinemabackend.common.utils.Paths;
import com.pes.chaplincinemabackend.entities.Admin;
import com.pes.chaplincinemabackend.entities.Customer;
import com.pes.chaplincinemabackend.entities.Movie;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

record TestCustomer(String username, String password, String firstName, String lastName) {}
record TestAuthRequest(String username, String password) {
}
record TestAdmin(String username, String password) {}
record TestMovie(
		String title,
		String director,
		ArrayList<String> crew,
		ArrayList<String> cast,
		float Rating,
		String language,
		String poster){}
record AuthenticatedCustomer(User user, Customer customer) {

}
record AuthenticatedAdmin(User user, Admin admin) {

}
@SpringBootTest
public class ChaplinCinemaBackendApplicationTests {

	private String url = "";
	List<AuthenticatedCustomer> customers = new ArrayList<>();
	List<AuthenticatedAdmin> admins = new ArrayList<>();
	List<Movie> movies = new ArrayList<>();
	@BeforeClass
	void initialize() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.urlEncodingEnabled = false;
		RestAssured.defaultParser = Parser.JSON;
	}

	@Test(testName = "Create Customer")
	public void createCustomerTest() {
		createCustomer("customer1", "1234567", "customer f1", null);
		User u = authenticate("customer1", "1234567");
		addCustomerToList(u);
	}
	@Test
	public void createMovieTestFailed() {
		createMovieFailed(
				customers.get(0).user().authToken().authToken(),
				"Movie 1",
				"Director 1",
				null,
				null,
				9,
				"Language 1",
				null);
	}
	@Test
	public void createAdminUser() {

		createAdmin("admin1", "1237");
		User u = authenticate("admin1", "1237");
		addAdminToList(u);
	}
	@Test
	public void createMovieSuccess() {

		Movie m = createMovieSuccess(
				admins.get(0).user().authToken().authToken(),
				"Movie 1",
				"Director 1",
				null,
				null,
				9,
				"Language 1",
				null);
		addMovieToList(m);
	}
	@Test
	public void updateMovieSuccess() {
		Movie oldM = movies.get(0);
		Movie m = updateMovie(
				admins.get(0).user(),
				oldM,
				"Movie 2",
				"Director 2",
				Arrays.asList("first crew"),
				null,
				9,
				"Language 2",
				null);
		System.out.println(m);
		movies.remove(oldM);
		addMovieToList(m);
	}
	void addCustomerToList(User u) {
		if (u.grantedAuthorities().contains(Role.CUSTOMER)) {
			customers.add(new AuthenticatedCustomer(u, given()
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.when()
					.header(new Header("Authorization", String.format("Bearer %s", u.authToken().authToken())))
					.get(Paths.V1.Customers.fullPath + "/" + u.username())
					.then()
					.assertThat()
					.statusCode(200)
					.extract()
					.as(Customer.class)));
		}
	}
	void addAdminToList(User u) {
		if (u.grantedAuthorities().contains(Role.ADMIN)) {
			admins.add(new AuthenticatedAdmin(u, given()
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.when()
					.header(new Header("Authorization", String.format("Bearer %s", u.authToken().authToken())))
					.get(Paths.V1.Admins.fullPath + "/" + u.username())
					.then()
					.assertThat()
					.statusCode(200)
					.extract()
					.as(Admin.class)));
		}
	}
	void addMovieToList(Movie m) {
			movies.add(m);
	}
	@AfterClass
	void deleteTest() {
		movies.forEach(m-> {
			deleteMovie(m.getId(),admins.get(0).user().authToken().authToken());
		});
		admins.forEach(a -> {
			deleteAdmin(a.user());
		});
		customers.forEach(c -> {
			deleteCustomer(c.user());
		});
	}
	private void createCustomer(String username, String password, String firstName, String lastName) {
		TestCustomer customer = new TestCustomer(username, password, firstName, lastName);
		given()
				.log()
				.all()
				.body(customer)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.post(Paths.V1.Customers.fullPath)
				.then()
				.assertThat()
				.statusCode(200);
	}
	private void createAdmin(String username, String password) {
		TestAdmin admin = new TestAdmin(username, password);
		given()
				.log()
				.all()
				.body(admin)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.post(Paths.V1.Admins.fullPath)
				.then()
				.assertThat()
				.statusCode(200);
	}
	private ValidatableResponse createMovie(String authToken,
											String title,
											String director,
											ArrayList<String> crew,
											ArrayList<String> cast,
											float Rating,
											String language,
											String poster,
											Matcher<? super Integer> matcher) {
		TestMovie movie = new TestMovie(
				title,
				director,
				crew,
				cast,
				Rating,
				language,
				poster);
		return given()
				.log()
				.all()
				.body(movie)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.header(new Header("Authorization", String.format("Bearer %s", authToken)))
				.post(Paths.V1.Movies.fullPath)
				.then()
				.assertThat()
				.statusCode(matcher);
	}
	private Movie createMovieFailed(String authToken,
									String title,
									String director,
									ArrayList<String> crew,
							  ArrayList<String> cast,
							  float rating,
							  String language,
							  String poster) {
		createMovie(authToken, title, director, crew, cast, rating, language, poster, not(equalTo(200)));
		return null;
	}
	private Movie createMovieSuccess(String authToken,
							  String title,
							  String director,
							  ArrayList<String> crew,
							  ArrayList<String> cast,
							  float rating,
							  String language,
							  String poster) {
		return createMovie(authToken, title, director, crew, cast, rating, language, poster, equalTo(200))
				.extract()
				.as(Movie.class);
	}
	private Movie updateMovie(
			User u,
			Movie movie,
							  String title,
							  String director,
							  List<String> crew,
							  List<String> cast,
							  float rating,
							  String language,
							  String poster) {
		movie.setTitle(title);
		movie.setDirector(director);
		movie.setCrew(crew);
		movie.setCast(cast);
		movie.setRating(rating);
		movie.setLanguage(language);
		movie.setPoster(poster);
		return given()
				.log()
				.all()
				.body(movie)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.header(new Header("Authorization", String.format("Bearer %s", u.authToken().authToken())))
				.put(Paths.V1.Movies.fullPath)
				.then()
				.assertThat()
				.statusCode(200)
				.assertThat()
				.body("title", equalTo(title))
				.body("director", equalTo(director))
				.body("crew", equalTo(crew))
				.body("cast", equalTo(cast))
				.body("rating", equalTo(rating))
				.body("language", equalTo(language))
				.body("poster", equalTo(poster))
				.extract()
				.as(Movie.class);
	}
	private User authenticate(String username, String password) {
		TestAuthRequest authRequest = new TestAuthRequest(username, password);
		return given()
				.log()
				.all()
				.body(authRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.post(Paths.V1.Auth.fullPath)
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.as(User.class);
	}
	private Customer deleteCustomer(User u) {
		return given()
				.log()
				.all()
				.when()
				.header(new Header("Authorization", String.format("Bearer %s", u.authToken().authToken())))
				.delete(Paths.V1.Customers.fullPath + "/" + u.username())
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.as(Customer.class);
	}
	private Admin deleteAdmin(User u) {
		return given()
				.log()
				.all()
				.when()
				.header(new Header("Authorization", String.format("Bearer %s", u.authToken().authToken())))
				.delete(Paths.V1.Admins.fullPath + "/" + u.username())
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.as(Admin.class);
	}
	private Movie deleteMovie(UUID movieId, String userToken) {
		return given()
				.log()
				.all()
				.when()
				.header(new Header("Authorization", String.format("Bearer %s", userToken)))
				.delete(Paths.V1.Movies.fullPath + "/" + movieId)
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.as(Movie.class);
	}
}
