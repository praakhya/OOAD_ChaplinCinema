var movies = [
    {
        "_id": {
          "$binary": {
            "base64": "XEvsErz9p5/V6jUY6YGfsQ==",
            "subType": "03"
          }
        },
        "genre_ids": [
          28,
          878,
          53
        ],
        "original_language": "hi",
        "original_title": "Attack",
        "overview": "With the Parliament under siege, India’s first super soldier Arjun Shergill is tasked to get hold of the terrorists in the nick of time, save the Prime Minister from their clutches and stop a dirty bomb from exploding and destroying Delhi. Will Arjun succeed in his mission?",
        "popularity": 250.549,
        "poster_path": "data:image/jpeg;base64,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",
        "release_date": {
          "$date": "2021-12-25T18:30:00.000Z"
        },
        "title": "Attack",
        "video": false,
        "vote_average": 6.701,
        "vote_count": 159,
        "movie_id": 799155
      },
      {
  "_id": {
    "$binary": {
      "base64": "JkBt0sBT2v1UeV9wJxwrmw==",
      "subType": "03"
    }
  },
  "genre_ids": [
    28,
    12,
    35
  ],
  "original_language": "en",
  "original_title": "Argylle",
  "overview": "When the plots of reclusive author Elly Conway's fictional espionage novels begin to mirror the covert actions of a real-life spy organization, quiet evenings at home become a thing of the past. Accompanied by her cat Alfie and Aiden, a cat-allergic spy, Elly races across the world to stay one step ahead of the killers as the line between Conway's fictional world and her real one begins to blur.",
  "popularity": 1445.91,
  "poster_path": "https://image.tmdb.org/t/p/w1280/95VlSEfLMqeX36UVcHJuNlWEpwf.jpg",
  "release_date": "2023-12-30T18:30:00.000Z",
  "title": "Argylle",
  "video": false,
  "vote_average": 6.128,
  "vote_count": 537,
  "movie_id": 848538
}
]
movies = movies.concat(movies).concat(movies).concat(movies)

var languages = {
    'ar':'Arabic', 
    'az':'Azeri', 
    'bn':'Bengali', 
    'bs':'Bosnian', 
    'ca':'Catalan', 
    'cn':'Chinese', 
    'cs':'Czech', 
    'da':'Danish',
    'de':'German', 
    'dz':'Arabic', 
    'el':'Greek', 
    'en':'English', 
    'es':'Spanish', 
    'et':'Estonian',
    'hi':'Hindi'} /*'eu', 'fa',
  'ff', 'fi', 'fr', 'ga', 'gl', 'ha', 'he',
  'hr', 'hu', 'hy', 'id', 'is', 'it', 'ja', 'ka',
  'kn', 'ko', 'ku', 'la', 'lb', 'lt', 'lv', 'mk',
  'ml', 'mr', 'ms', 'ne', 'nl', 'no', 'pa', 'pl',
  'pt', 'qu', 'ro', 'ru', 'sh', 'si', 'sk', 'sl',
  'sq', 'sr', 'sv', 'ta', 'te', 'th', 'tl', 'tr',
  'tw', 'uk', 'ur', 'vi', 'xx', 'zh'
}*/

export  {movies,languages}