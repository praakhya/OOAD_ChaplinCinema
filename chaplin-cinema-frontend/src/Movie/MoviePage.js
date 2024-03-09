import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
export default function MoviePage() {
    var movies = [
        {
            "Title": "Avatar",
            "Year": "2009",
            "Director": "James Cameron",
            "Writer": "James Cameron",
            "Actors": ["Sam Worthington", "Zoe Saldana", "Sigourney Weaver", "Stephen Lang"],
            "Plot": "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
            "Language": ["English", "Spanish"],
            "Poster": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/7QI+UGhvdG9zaG9wIDMuMAA4QklNBAQHQ2FwdGlvbgAAAgAcAgAAAgACHAJ4ALlBVkFUQVI6IFRIRSBXQVkgT0YgV0FURVIsIChha2EgQVZBVEFSIDIpLCBhZHZhbmNlIHBvc3RlciwgdG9wIGZyb20gbGVmdDogTmV5dGlyaSAodm9pY2U6IFpvZSBTYWxkYW5hKSwgSmFrZSBTdWxseSAodm9pY2U6IFNhbSBXb3J0aGluZ3RvbiksIDIwMjIuIKkgV2FsdCBEaXNuZXkgU3R1ZGlvcyBNb3Rpb24gUGljdHVyZXMgLxwCBQANTUNEQVZUSCBXRDAxORwCGQAGNU1PQzIyHAIZAAggU3RlLURPTRwCGQAHIE1vdmllcxwCGQAMIDIwMjIgTW92aWVzHAIZAA0gMjAyMHMgTW92aWVzHAIZAAcgU2VxdWVsHAIZAAcgU2NpLUZpHAIZABAgU2NpZW5jZSBmaWN0aW9uHAIZAAcgUG9zdGVyHAIZAAsgUG9zdGVyIGFydBwCGQANIE1vdmllIHBvc3RlchwCGQARIDIwMjBzIFBvc3RlciBBcnQcAhkADyBBZHZhbmNlIHBvc3RlchwCGQAIIE5leXRpcmkcAhkACyBKYWtlIFN1bGx5HAIZAAggU2FsZGFuYRwCGQAEIHpvZRwCGQAMIFdvcnRoaW5ndG9uHAIZAAQgc2FtHAIZAAsgU1VCU3Qtc2l6ZThCSU0ECg5Db3B5cmlnaHQgRmxhZwAAAAABAQD/4RVpaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/Pjx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iPjxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+PHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9InV1aWQ6M2U1ZmVlMGEtMGEzNC00YjY5LWI2ODYtOTYxYWI2ZTNhMDY1IiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnhhcFJpZ2h0cz0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3JpZ2h0cy8iIHhhcFJpZ2h0czpNYXJrZWQ9IlRydWUiPjxkYzpkZXNjcmlwdGlvbj48cmRmOkFsdD48cmRmOmxpIHhtbDpsYW5nPSJ4LWRlZmF1bHQiPkFWQVRBUjogVEhFIFdBWSBPRiBXQVRFUiwgKGFrYSBBVkFUQVIgMiksIGFkdmFuY2UgcG9zdGVyLCB0b3AgZnJvbSBsZWZ0OiBOZXl0aXJpICh2b2ljZTogWm9lIFNhbGRhbmEpLCBKYWtlIFN1bGx5ICh2b2ljZTogU2FtIFdvcnRoaW5ndG9uKSwgMjAyMi4gwqkgV2FsdCBEaXNuZXkgU3R1ZGlvcyBNb3Rpb24gUGljdHVyZXMgLzwvcmRmOmxpPjwvcmRmOkFsdD48L2RjOmRlc2NyaXB0aW9uPjxkYzpzdWJqZWN0PjxyZGY6QmFnPjxyZGY6bGk+NU1PQzIyPC9yZGY6bGk+PHJkZjpsaT4gU3RlLURPTTwvcmRmOmxpPjxyZGY6bGk+IE1vdmllczwvcmRmOmxpPjxyZGY6bGk+IDIwMjIgTW92aWVzPC9yZGY6bGk+PHJkZjpsaT4gMjAyMHMgTW92aWVzPC9yZGY6bGk+PHJkZjpsaT4gU2VxdWVsPC9yZGY6bGk+PHJkZjpsaT4gU2NpLUZpPC9yZGY6bGk+PHJkZjpsaT4gU2NpZW5jZSBmaWN0aW9uPC9yZGY6bGk+PHJkZjpsaT4gUG9zdGVyPC9yZGY6bGk+PHJkZjpsaT4gUG9zdGVyIGFydDwvcmRmOmxpPjxyZGY6bGk+IE1vdmllIHBvc3RlcjwvcmRmOmxpPjxyZGY6bGk+IDIwMjBzIFBvc3RlciBBcnQ8L3JkZjpsaT48cmRmOmxpPiBBZHZhbmNlIHBvc3RlcjwvcmRmOmxpPjxyZGY6bGk+IE5leXRpcmk8L3JkZjpsaT48cmRmOmxpPiBKYWtlIFN1bGx5PC9yZGY6bGk+PHJkZjpsaT4gU2FsZGFuYTwvcmRmOmxpPjxyZGY6bGk+IHpvZTwvcmRmOmxpPjxyZGY6bGk+IFdvcnRoaW5ndG9uPC9yZGY6bGk+PHJkZjpsaT4gc2FtPC9yZGY6bGk+PHJkZjpsaT4gU1VCU3Qtc2l6ZTwvcmRmOmxpPjwvcmRmOkJhZz48L2RjOnN1YmplY3Q+PGRjOnRpdGxlPjxyZGY6QWx0PjxyZGY6bGkgeG1sOmxhbmc9IngtZGVmYXVsdCI+TUNEQVZUSCBXRDAxOTwvcmRmOmxpPjwvcmRmOkFsdD48L2RjOnRpdGxlPjwvcmRmOkRlc2NyaXB0aW9uPjwvcmRmOlJERj48L3g6eG1wbWV0YT4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKPD94cGFja2V0IGVuZD0idyI/Pv/bAIQACQYHCAcGCQgHCAoKCQsNFg8NDAwNGxQVEBYgHSIiIB0fHyQoNCwkJjEnHx8tPS0xNTc6OjojKz9EPzhDNDk6NwEKCgoNDA0aDw8aNyUfJTc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3/8AAEQgAugCUAwEiAAIRAQMRAf/EABsAAAIDAQEBAAAAAAAAAAAAAAQFAgMGBwAB/8QAQBAAAgEDAwEFBgQEAwYHAAAAAQIDAAQRBRIhMRMiQVFhBhRx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",
            "rating": 7.9,
            "id": "tt0499549"
        },
        {
            "Title": "I Am Legend",
            "Director": "Francis Lawrence",
            "Writer": "Mark Protosevich (screenplay), Akiva Goldsman (screenplay), Richard Matheson (novel), John William Corrington, Joyce Hooper Corrington",
            "Actors": ["Will Smith", "Alice Braga", "Charlie Tahan", "Salli Richardson-Whitfield"],
            "Plot": "Years after a plague kills most of humanity and transforms the rest into monsters, the sole survivor in New York City struggles valiantly to find a cure.",
            "Language": "English",
            "Poster": "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBYWFRgVFhYZGRgaGh4aGhwaHBwaGBocGhwaHBwcGRgdIS4nHB4rIRkaJjgmKy8xNTU1HSQ7QDs0Py40NTEBDAwMEA8QHhISHzQrJSs0MTQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ0NP/AABEIAREAuAMBIgACEQEDEQH/xAAcAAABBQEBAQAAAAAAAAAAAAAEAAIDBQYBBwj/xABEEAACAQMDAQUGAwQJAgUFAAABAhEAAyEEEjFBBSJRYXEGEzKBkaGxwfAUQlLRBxUjYnKCouHxstJDU3OSwhYkMzTE/8QAGQEAAwEBAQAAAAAAAAAAAAAAAAECAwQF/8QAKREAAgICAgEDBAEFAAAAAAAAAAECERIhAzFBEyJRBDJhcfFCkaHh8P/aAAwDAQACEQMRAD8A8hmklcmitDpnuHYg3MFZ47owil3MkjhVJjrBpARBeM/Wjgvd+Vd0egd2tqELB1ZkwAGCb95BYhcbG+lEIoZQRwePTpWc2XFESpP1p4Si7WkbYH29wsUBkZZVViImcB1PHWu+6rJyNFEFCU4W6OvaN0IDCCVVxkHuuAynHiCDTRZqXIaiDBK7sqwfROm3cI3qHXIMqSQDg4yp5zimfs9LIpQBrSHyjz2z8t3NSi3kzH1t+Q9BRVmwxxiB/hB+W7miv6ucoXjuzzNsAkQMAmWiVmAYnPNLIeJTXVPXb8tv/wAeKj2VY37Z4MR0wvnztqNdMxUsASqkBj0G6ds+sGmpCcQHbXClWD6VgqsQQGnaeh28x6TUZs0ZCxASlNKVY3dGyhGYQHBZcjIDMhMDjvKwz4UO1qqyFiCFaaVq5vdh3lTeyptzn3lo/CAWAUPJIBEgCRIqsa3VWTQKy1yKLOlfYbkdwMELSPiYMyiJnIVjx0NR6nTMh2uIO1WiQe66h0OCeVZT86oTBwKVP20qBFc/H6H2qw9n9X7nUWbp4R1LDxQmGHzQsPnQ2oEnrERyI/WRUlhRCnbPI+KOAYPH8/tWl6JS2bJe1rG29aEsLKm3pCeWS4q2L5xwXVS/q7VWJZlOP1PnQOjtpJ3GWgEbCYz55/D8Aa0Gl0vcA8orm5p0dPHHQ22o/ZkSRuF5yVnIDW7IBjwJtsPlUa6aptNZ58hP0M/gYq70+gmubk5KNVFA/aGhD7HDoR7u0m0NLgrbVWlekFTQ69m1pNL2bnIqxTs4eFc8vqN6GkkZ3Wdl7ktMGXuWwhWe9O+43wx4MKD/AKqPhW4Ts4eFSjs4eFQ+dsFJIwg7OYdB89v51YLpgbDK5Q7RNv4d6uWG5YAjYRJzwRjJIrVHs4eVDt2cP1H8qcedrsG0zB3tK3BA+QX8Vo/stES26P8ADduIjjkhAlyHA/uu6N6pFaVuyJOaZc7HHhVL6imDxZnO1Ar2raphbbuiL+9sK29rkeLMrsfNjVQ2mraHsnyoW92VVeumwUY1Rm+0EBSwAQStsqQOQffXng/J1Pzqv9xNaLUdnEdKgTQHwrT1kLArtTZB09pARuW5eJHUB1sBTHmUb6VVvpz4VqH0WKifRkCCKpc4vTRTaa8qaa4pS27G9bYJc3/CqXgXAR1OC6jmO9x4DdvQ97eu2DbsCEJKqU09pGUSSe6ysuSTjk0dq9JHSgVtZrojy2jKXHTK33ddqyfTUqrMnBmf1FmC26SfAcZx1zzI6VIhcqvdZQPLGOpnnj5UrurDAjaBPIEkTnImW69W8fkdpr6Oqq0ggYH7h4EnPgOuPy1cml0RFJsM7P0pII4ETyCSepkjMHx9M1suz9DKIf7o/Csz2boRJKwTAkD1jB6j7ZFeg9mJ/ZJ/gX8BXm/Uz+DsisYlDptJF6Pl9ZH/AMR9a0fZ2l7oB6Y+hj8qr9aoS8H9Bx1A35P+U/WtBYjcfOG+0fkPrXLySbSE2TW9OKifSuCxTksYkyNvu8QCcd/9RRqGiFioiiGwRLbhW5J3iB3MpuEgZ/hnmK7tubvBd4IgrISCp3TxwHxMzHiQaGoVdEArrMh02HHkVJPiYMfIVvHEh2RMlwIgBBf9/cVz3TPwxwxBwOkdZpltLp/h4SMg5AG7dH8RMCJiPrPb7PAIO4kSDmC2HZwA3q0GZJAHWSZX0QKBAxAB3SI5BJGCOjQf8oqko/8AIWwR7Lwmc7RMFI343bp5Xn4c8+UMKPtYEjdC7Y2zOScHE9OYMTiYBN7SeLDlumQGffgzhp6jyxIBqBNGAVIPwkEA8YDDEEfxkwZFRJRX8DVkFyxdzAAErGV3bQDuDDiSYIg9eRUF3T3ARmRuaZ28b02xEfub/wDmKuw9DXYrJy/CLQA2mBHFcHZynpmp21AHNcOtEY5qCtldqOz1HJig7iqPA1Pq9QTiarWarimy1Yy5pVuErgeBqs1HYu2eDVizxmmjVGZOa2i5LobRTHRkYNKrO7fnmK5WuTJpHlzrz+p8I8aNtqJVQJJGR8XCye74c5P5UJcEmZ9OTMcxt6n6Cj1U7V3IVIGAwMZjvDd0MjmeOor1JHDHsuuzdWyEwFhviJEscpjcOcqD4TPz9D7DvbramI5+kn/evONBaZipgkQRuEnwxI/KK9E7E/8Axpz+8DPPxEifv9a8z6pKjti/aSdrpKM/gd2BJO2DHzCkfOjtPdEIR/hPoRj7hfrTWWUjxM/fd+VBdlIdjJGUwv8AlMr9tlcncf0BepdohbtVwPWpENRZLRYo9Sq9AI9TB6pSJaDN1Lf5/ehVuVIH8/xrRSCjtw0M5ipGaoHas5OwRDd1MUK+pp1+gbq1NGiQrt6oTdNIrSKTVUUQuZqB1o1bE11tLTToLKx0oa4tWr2qEupWkZBZXNSoi7brla2I8nDeH+1GaftO4o2kh0x3XUMuIjBGDiJoR1g4n8Km/Zjjx8D6A/mK9h15PPVl9oO3lICMpQ8BlY7dxjLIxjbgd0QBHlW17E7SdUCukrzuU7wDiMpImeleYLpGxgZ6yIxH86t+x7NzdNp2T5wDz9Ria5ebhjJaNuPkktNHqum7RRtiodxA4HOBHHzoRNZsuk52vI9CMYH+Hd/7BWR0iFGHv3d5IE3CSvko3Ezk/OfKrOzqA6k7p2EDEgyTO044gfc1wy4VHraOhOzZ6bVKceBI+XI+xFFq4PFZrSufqJx5dfoRRqMelcso0x0XQFSDiqy1qGo1bwNIlonVvL8fyp6t+u9UST0Hzz94qTjP/fNXHolic+X4/nQrtU73f1n86H3Col2NA7ioXSjWg1A5FBaBDbqS3ZpMakR4oGSJbFPNmaal5ZqRLhJxSEC3tNQ7dnzxVs89aFeRVJsVlLqdKV5pVZalC3NcrTIdng10g96IEnrPUkcz0IHyqxsWhgxAxLMCQJByAPixBwT0qrAM4/nxmj1us20ByGyAf4Rye9lgMDJiIMV7rOBFrYNpyhDtJALqVbaDPAkkTjw68QMx9q3GsuFRgAVDGBAJJIyvB4xil2czbxJkcbpEGJkBicEheOpB86L7VC3LyoRAKqc4znqROeJPlWXUqfRr/ToF0ntE4ID7XQGcwD9Dj7Ve6e4rbHtsCgJLKDuYE/WeBHGfDisl2l2f7pyhIPXuw0TmCQYmmaPUG2wZZ+Qz+vOifFGSuIo8ji6Z6t2exViThD4zILRBzxkkERPHnV1bQEArkHOMj6isV2D2jvYPLMY74aMiASSCRBG0+Mj7azsrtgEpaZBvZ9vdIhVJiYHOZ+RBMZry+XilZ0qWrD0teVTJbqz/AGanLpq58GGQPYQ+H41K9k+H/V/OikteVP8Ad+X41rGDohyKm9bPUfj+ZqvvEitG9melC3dHPSolBoqMigW4aifUVdvoKCvdnVH7LUkVRc1NaY0R+xEVImkIosdoCcwaet8jmjX0tQPpadhaJrfafiOlMfVknihTpTU3uiBTFSE2q8q7QboZFKqpBR4WZ/lRWnUkxx0MYJ+nr9qjFtSTLR9Pwn061cvpQr42CQCFZlDDcwECMA4M8+Pp7smkcMVZPo0O8CIA/vDPIzAmeuKuRqQpIZUIBGGUlgSrKCJ9eSeswcUFp7BVkkEH4ZwyAnvAF0lYg4GOc1bWBDlXVe8o/dE4UEmWEkA5jNcspq7OmMfaZgme4Cu0SNzAMdpYfvRJ655yYp2n7KL7gjFiqlzjG0Az3ieRB8MgxW50/seL3fGxEkgGCfhYgyu7HHiRzitRb9mhBUJa707jtCnIIJ3bSQc8iI6VXrrwZ4b2eOdjXhbuLO85HoM4PEj/AJkV6D7N9m3HvhwqNkP3iVOwFZBKgie8CAIB2zRX/wBIujBGNsJtdt+zcWiGCkAqdwAy2AZMDMDWey1tBbZQq4YhsCSULJnx+Co5GpPQLSouFtD/AJJP3NP93T1SnVnggsi2eVc21KajI/UVDpARkUqeUFdCVPkCJ7YqNrINFe7rpt03x34HkA/swpp0tHlaWyp9BBkysfS+AFRvpccVa7DmQPl1qIISMiD1HP361EuEakVJ0vlUb6byq2a3FDvbrNxotMrDpRmaVF3ExSqQs+cdPG8ccjldwif4YyPKtRa2oJhVIOCBABktuAb05jEVlrbHesHqP4uf8pmr7T6ZiwVtimQJIXkx8IYlifp+IHs8qT7MeN0aHsj3SOG/eYGWLKSQTMMVUCT4DmOKtO11QvauqAAGCviOd0GD4Fxnrisxpb7BwNqAQQCqkFpI6ySR6Rya3HYtlWss7KDtIAJHeU7rZkEjnu1w8ixkmdK+00nsmQ1jIhg7yDE5cspweoIq9WyOlUvs4CtvbjhWkdQyD74q5VqmM0Yy7INWnfs+BdlPmDbuH8VFVfs1ei7dTjvEjzBW2f8AqZ/rVtqxm2Z4uA/6XH51SdnjbrXE4lvvv/ktXnvRNaNUGpGmiu7aeTYqOba4Up2004CljfgLIvd04Kafmu0RikFnAa7upTSrVMQopV0Csn7bdoXbJtm3cZAwaQApErBnI5zTbxVjSt0asimMK8kv+0eqbB1FyPIKs+UquKrLnbWp/wDPvxHHvGA+k1N2Vi0e0utC3a8n9mNU76qyHe4ZfMu2eTkzkd0Y8q9UuVy8/tdFRQJfelUeopVzWWfOdhJdRHUcwPqScVotLeOAHnoQuAsdGKHJPexnjzFUNrDjPXoc9eCBxV8gUtuHG2O9MY4jgDHSOnnNe7ybOfjD9HaYuo2kSD0KyPuSK9C7EtAaRyON4JkMOmcMSeYrBaW+24L4KAQAqkQT/CpIz416Z7PW50bAdXHjwGBIz5A1w8ibas6ZP2lj7P6bapT+FUT/ANgK/lVv7s1WdikyfP3h+l1hV1RGCaMG9gWqXA8iT9FY1Q3xt1yeYP2Nv/vNahviX0Y/gPzqg1qf/eoY4B/Gx/KhwSBMvwtOAp8V0LWseJkuQlFKnha7trojxuiLI6VPK0wipcGux2cIpRXa5UuKGKKxH9I1zb7gRMl/wWtvNYX+kZZawBM9/wAf7s8VnOsS4fcD6f2UB0RuNm8w3rnAUZC/MZnxigPY3sBL5d7qyiDYFzlmGSSD+6PuQelb/R501vztL/0Vn/6Px/YXP/UJ/wBKj8qxf4Kt0zHaTQfs/aSWZkJcUKTyVaWQ+sMPnXpbzXnvb6x2sv8A6lj55tifKtb7Rdp+5hADNwNDAxtIgTwZOQflWfPFyp/gcTuo1STAdSZiNwmfCJ5pVl7XZ4UEMxaTM5Ef6TSrDBfJpR5DpT3hH6+9aKwkiWwIwQAQBAECQcYGfXjFZvTsd3P5fkat9JrbgJUOwnmJjOPhA+Ve3yRb6OSDXkvkIDgIwI2iILlh1MgCJz8q9T9jSDoyTEhn8cYnr5H715JaBlCCNxJnuNPzBxBx+jXpf9HUmzqEP8ccRkpnE+QrllE3b9poexfiI8A/3ct+dd1WpvB3CldoKgSOJCST45dfvUHYt8NdcD+EHygqv50tfchr/kyf/wA9Zy+3TI8lzppKoSZ7mT5nafyqo1f/AOxP9x/9Jtj8qttCZtp/gX/pFU+qP9sP8F77PbrRdInyaKpFFRg1KK6oKyGdqssdsI1z3Q+LPUdATkegNBdse1WksFrdy5LAd5VBYwYmTwOeprB+y3tDYGr33RsVgShA4ZypG4jLCCc/lVybTSQJJp2euVHcpWrisAVIYHggyD8xUd9qXK0o2EexnvQZClSQOJn7CsR7Qe0epsEK9u2UYkDa7IxjiCcDkdD1q7O8PvQD7D1wfWrYAMkXlXOGVoKn5GQa4FLN0/8ARpWJ45ru2Hfc4d7S7YEtIY8mDAgTMnwI8cw6ntcBB/bNcYEGd5JHEqDAO3jA/LHs+l7Ns252WkWZnaoHPIx0wMeQryn+lHslbeoR0VER0+FFCw1s95iAIyHX6VouOOkGT8AfZvtnctgIHbaOhyPp0HNaX2O7fsojKxA3Nux9xH+/WvK6lsvtMjmrlwRfWgU35N57TapG14uIwIBtMTxG0oTM5GBNXvtw/esn/H+KV5noFLkmZJnJjOCOtaDWdtC5ttu5m2WUboBMECfAztHXwxWM+N2l8Fxl5Ld74OR+X5kUqrbepHHjH64pVj6ReR5lYPe4n6/lVjYchvCPXPkAT5xVdp171HW0HT8v5V6skcqLnTwNhCw0k8HqZx3uPpxW69j9XqLaMtlQ++5kbM4UcHcBx48YnmvPbMCOv08vKvQvZq5t0rEEg+/ExAkBFJ49a5Oa4qzWLtUO7F7X1CXSvu3ZygLABWYAbR8IHHGZ8Oamv69WZmc3FffLqRtyAAqlJ4EDHl0p/ss23VWzPNh58++og/Shu07qNevuCCC/dIz+8mQfQGsGrKXZoND7QBLaLzCgAsrycxyJn18arn9pUN393/xh8RHxtbIwV/u8U/VaoA6fae6W8cRK8VRantH+1YgAgNPXqcx9KIOTE6N/p/aG23UEz/EpxM+I9B6VL2z7Qpp9O9/4tg7qyBuY4AmfE/QGsHc7YB/8NJ9AYyfH1+1B9r6z3ttlKLG4vACgmJxuIIGCeldHFKSasiSVGH/a3d2d2Ja5vYk+LOWJzx41ddvhU2ODJKkEwQBEAAYyIP8Auapr5VboCqBsCBRknACgdJ+ESes+dE+1LSNzTOzAIb16jHpjmut9ohdHpn9FXbJuLftEzsKMvowIP3UVsdXqh5fqa8i/o+b9m192zuMPZQzHJZbdzjwywrY9p+0SoxSCSpicDx4z5+Fc3PJt4oqKLk6pdvKzHy6fzrPjtNveOGclVLQGYlQZIwOmKrNR7UHbCqQ0RLMD4dAvketVdvV7yxPLTMHqTP8AOuX0nWy00Wun7cvtdXdccLPCHbhRx1J4FB+1je8dJa42GHfaYmD3ZGBihkJRww6Z/LqIpam975wWxic/ToMVSVSTXQzNXtLAHoPwpLpcn9eFXWq0UW1PUAUNasd458a6FO0Q4kWhtwD/AJvwoS9YZmcjMMfvuP5GrPR2CynwlvzptzTlFZxwYJBU5weD/mNCe2DWit0hfdtyI5B4EfhSrl5Wbc4Ux1IB2j1PSlWlE2UGnBmjbaHw+9QaejEIxmtWJBtjSsQCYx6+XiDV8l1EQ7t3JAKkKASo8vKqbTXjx4+lEapyUPiI/XFYSjb2aRaSLG52nChkdQfhAHIUmTugcYI/zVHbCEl5ZGGWBLFAScMCvT7Z6VTadGM7VY9O6CR/pBn0olNWVMd/GD3NsT0M5z6VDgl0ClfZok1yFS28EKeVDiDjJG0QTFQoqud6sT3hI44POczQej1u+QiOW6FiQAMZwAD1xVlcKW0m47bhBAACnBmcLxIH2rJqtItb2d0yIzTLbYHd2jw8Zqe9dthfgY+QVTP1PnQKaT3kOFIQkk94ksZJMdBPhU1zSWklmGBJ5J9etNNWGDZi+29UvvGKnCjuhgAZExgeG70wKBGre6wDMSuRGImD0HnQutuFmZiInI8h0H0otdK1plQ/H3HjwlVePvBrtVLRg2zRaG47anfkP7lBIGR3BHA/hIq4bSlviYqPFlY8Dnug/SP51y12MgMkEkxI24OAPCOnPrT7+i04U77cDxgY+cYrmlOLejX02Fab2fLqGF61B/i3A4MZVlBBqzs+zKDJvWvPOP8AqHnWPu9nW925AjAfuuFTMg/EqmfCCvXyp2gspcaGsom3DCA6k56xxBB5NQ/2NQZtf/pfeTsuIT4KsxIxgPihtV7NahIKMpjxBHHT4zVUvZiMS0Sx6pKny72PxoPXdo6+2yrZd4H8bhj6Q+PrUx3pP+43GSCr3s/qTgovjG4+vE03U9kaomSiAkRhjxnz86gX2s19vaby24OJeFb5skgfSi9Z7equzuIZMNtuo4HPG3P1Aqqn4SZD12D2uytSgjamZ5kkz86j/qK8WDME9IJU+oq8sdt2roBS+hkcbkDCehU5qa9qgoksQPHEUs5J9DpGefsZ8yEyegdR6QGFKrW5e/vN867VZSDFHllpgMk4qa1ck4BI+lQ20HgKNsqorqbM1EttEd1tkJVTIyqZ255JwTx4U89npALsX4HJ9AKFt3gPD50zUXcSpxgkSJiegPXz/lWNO9GrqtotNAbYY7QEEkTuIG48HBzgfcVpdJpEA4mckqWXPOSDzivPdJrtr5yFjH7snkgR5mOT40QO27jEBWO3dPiT1yT056H86ifFJvTHGaS6Npr1dVK2Q+4/vF5583kj5VSWOzySTeViT1LBpPoR+dc0vaBcjvyeskk+X49MRFN16m5AyAGkczg8wOtZJSi8f8muMZbDEfZhd0eLNu+gNMJLAhuDgxtGOvSh9PpTgBuOpxVxpOz4gyCfMH8QaTkol0Yf2r0wS4nIVkXJyYXun1xFWPtCT+0rHDqkjiADtwPlU/t5ootpcmYcqwjowmZ8JWPnVNpdUb+o04PM20PXg5Oa6oNyipfFnNJJSa/Rt7mogypJ+v8AKudt9p20slH3F2cAwfhAIJzxECPnUt3TCJLkgmORJPoBWf12mZkUTu3uMnlRuCd0nptK/Subjxb2bz60G2/e3kYMmxGEowZT3WPdkKPAjnmitDojbVl2sxJncAhB8oBB+3WpzdhAgWVAAHOAMDk061rJ5EH0mplJtaRSjRXXtQqHvFkM8sjLHP7xER5zVhprm5QQ0/KQR5QfKnXrm4fzFVWo0wEkLzyVZl+pU/jSXuKLLUFgJkAeADR9N1VOt1jEQCD/AJARURvMshbjcQFch1HnJhun8VQXmLydi88o8n6Ej861jGjORQ6nROxJkc9IH2FEaZWChWS8RER7xVVZMyFAznx8asPcqeTH+IbfyzTnsnbKnHiCK3yvTMMEuitTV6lBCMdkzDMGMeHkPSlU7AjkH60qq18E4/kpkeiFegEapVerIQYDnkx61KTuEbjn0oIPT1vDrUtFqgpOzkMSTR7aJAoMvPQkSo9fCq/T6xBGGP1/QqW5qmckBVCxHeJJ+QH64qGpMtOK6LGx2ciQ+S3O6T9o6VYacE4g+vlVXa1aqFAWcDPUmOcnE8xUh1Mn4IHXP8ufpWcot9mikl0aNFAAJDR0EGW+U8VY29RtBgkecj/msYLsmQzDyDbTRja07Qo6DkzkfKsZcNjcrLHt/tK01l1YB+4wExMxiDHjGaxXsum3UIzGFUMenO0rAkETmZ8quLjhlO79fOov2RDws3AfDvAcGfAziuiCUIOPyZSTbs0eo1UiVPTwH4gCqm47gpAwpn4pxtIiDg9PvUWmBUwZ9DI+XFWJ1qgbTK+ZAPhiY8/tWWOPSNNsiXtE/vSKc+tQ8E/6l/X/ABUV/VKThVj1/GhtRexhZ9P9qFFfBVhpupzuc+jE/ac0l1KgGN0ec1UEzmAPLz6+n68K6WjoCPMZ+sGqwQsiw1NwNQJcjMgfUfbimvqgeSPzoR9WIPU8Z8/+RVxgyZTQdJcjvDx8fH5U+/ZU9F3eIG058CKrU1CxP5+v04FSLqsnoPP9eVPFk5JnNRbYcMPRhI+RgEfelSfVA9KVVsmzPB4rm8nimAU4GtqMR4TxJNSIAOlQ7q6Ho2CJ91SW75HFC7qcGqaKTLS3rDEEz/zPWjtNqgYkwJ55j7ZqgR6Jt3alxTLUjW9qaUWGVGZWLKGxIweCZEziou0dN7tihdGaRKgOSJAYcgA/71P7YPF61/6CHx/iqHtjttXcOjPgqVR1UKu1QCQQxkkgHgcmskm0mVkcvaUW2Ks9sOgkod3IE7J2lZ6RMTiaJ0l99raj3iLJFslpknaD0BEwvPj9agvdsadnu3drb7lthsKoyK7D41ctMBs/DND9n9rolg2t9xGNzfKBSCAu3adzjx8x5Gni6Cx7WHe2+oE7A5DgcrMGdv8ABLR5URoOzXuXUsJdQPcth1kMFKld4BIXB25zVf2f2yLKKEJ3i4zEFQUZGVVKNnIO3iKvfZvtFLvaVp0QooQqFJnbtssCAeomY8qGmrCwJPZ17ob3d21eZASUUuHgckI6qWA8vEfOjGpKfDHOQeB9qtfZa8/7fZ2gz72DHMSQ3r3d1A+0F9f2q+yAbTdeI8N7ceVVW6FZB+1H+FfMyR58HpMcULqde2QBBMiCZ6R1rjX/ACqNrg/h+n/FUor4E2we9qGHxBfUE59Mx84prMSJgdefP8aToD1jy/2NRE4jn1/L71ojNkyaiIxMj0+36NL9qPPoP19KHPAIz9qbOKdIVhTXT49Pl50qG38UqVBY2ptJZV22s6oInc0x0xjrn7VBNKaoC3/qq3MDVWyZgAKxJnwAo9fZxDJF1yJjCHxg/MEEEc4qtte+0yJdBQJdgiCjFgpM4IJWDIPga0+h1Ju6d7wW5sQszxsKoVDNO7EkgjoOWGeqArG9m7Yx7590Ege7aTt5gcxkZ865e9nECFluOTuAgowwWAkwPDpVnbv29RDquofY0AgKNhYgtIBEgwOcYjrQt32jtbSFa8SIKghclWDAE5gYGc/TFILIj7PWxzecKASWNsxCxM+HJ58Kdo+xbLKGW8WAMNCMVkESCVPgZ5BilY9qA2H94giO6UeZwZlBFO0/bOmVndTeBeJMKZgAfDwOORQFmg1OqLsGfYzIoCsdO0qFyOT0J6+tUWp7Itou43m6ZZGUCTGTTdV2/bYbVu3lndPcQzIwII4n9eEl7t+w4gveIwYKpEqQV48CKlRS6Hkw8XbiwrFI4kWXn4GIJk8HbHqa7fvMql/iaCu0Wn7wV+7ENyQTQ+r1pKK1sXHJ2MVLWlYo52qV2gt8ZQcdfWJRrmKlmF0wqt3PdOsu5RdrD4txEAR04qqDIbqXtb/7R7W7bnfaIIEngM0x8Xlio+wNtq57204ZlYop2MwEhQTggSQzYzj1om467S7s5AQvIazJCBGwN2cMjDx3LHxCqqz23aVGAe7uZmYnYkSWJBjxiB8jScb0wyLm7dKkm2Utbw4a5bsuHA6gM7HZOfhj6VS3+xECM+9yVBIUW2zHAB/XNS3PaK0RAe7BOZVOCCCP14mut7Q2DjdeK+apOB6en6FCikGQPZ7ERhO+4srIDWyGmTIIBIEd3M9ad/Udsgf2z5H/AJbc+HnnFSN7Q2sd+9gnlUnaSvGMGB9qG1HtGqlRb3sgDSrlV70ja07TOJx6eAp0Kxa7sRETcLpnu/GjIg3MF7zRgZ+1SH2etmP7V4PX3ZIiCenXFTr7TWTG57uI4VIx/KW+tS2u20hd37QWHX3awPCMcZj5UBZV3uwrKEF9QyqZyyEZG2AJ+I5OeMVTdpWURgqXBcG0EsOAxmR58fejb7anVOtvvuCx92GUKADMEtA/dHWgO0+znsOUuABoBwZBB6g/I1QA1KmxSoA7T7N4qdyxPmAw+jAimUooAK/rK5jK447if9tOsdp3URraOQj7tywIbcNpnHhx4ciDQdKgQVpu0bqAqjlQedsCeeTEnk0OWnOPkAB9Bim0qBnZroNNpTQA6aW6mzSmpAt9N24yp7tgWEACCqFQoIUAhCZG5szPeoi37QwmxUcLtCBRdIUBTuGNnIOd3OBnFUE0pqhFpq+1i9sWwpUABfi3So2kL8IPKJkk4RRwBFdupk0ppDHbqW6mzXJpAOmlNNmlNUA4mij2nd/jP0X+VB0qADP6zu/xnBBEQDIMjIHlUOq1LO7O7FnYyWPJ6fgIqGlQAqVKlQIdXKVKgYqVKlQIRrlKlQMVKlSoAVKlSoEKlSpUAKlSpUAI1ylSoAVKlSoAVKlSoAVKlSoGKlSpUCP/2Q==",
            "rating": "7.2"
        }
    ]
    return (
        movies.map((movie) =>
            <Card className='d-flex flex-row' data-bs-theme="dark">
                <Card.Img variant="top" src={movie.Poster} style={{ "width": "15%" }} />
                <Card.Body>
                    <Card.Title>{movie.Title}</Card.Title>
                    <Card.Text>
                        {movie.Plot}
                    </Card.Text>
                    <Button variant="primary">Edit</Button>
                </Card.Body>
            </Card>
        )
    )

}