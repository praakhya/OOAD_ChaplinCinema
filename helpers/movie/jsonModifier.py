import json
import requests
import time
def combineJsons(srcpath, destpath="combinedJson.json"):
    allJson = []
    for i in range(1,501):
        f = open(srcpath+"/{:d}.json".format(i),"r")
        jsonString =  f.read()
        jsonData = json.loads(jsonString)
        allJson.extend(jsonData["results"])
        f.close()
    f = open(destpath,"w")
    jsonString = json.dumps(allJson)
    f.write(jsonString)
    print("Written to %s"%destpath)
    f.close()

def changeImageBasePath(oldImageBasePath, newImageBasePath, filePath="combinedJson.json", destFilePath="newCombinedJson.json"):
    try:
        f = open(filePath, "r")
        jsonString = f.read()
        jsonData = json.loads(jsonString)
        for object in jsonData:
            if (object["poster_path"]!=None):
                object["poster_path"] = newImageBasePath + object["poster_path"].rstrip(oldImageBasePath)
        f.close()
        f = open(destFilePath, "w")
        f.write(json.dumps(jsonData))
        f.close()
    except RuntimeError as e:
        print("Unable to open input file")
        print(e)

def populateAllActors(authToken):
    allActorsJson = []
    with open("combinedJson.json","r") as f:
        allJson = json.loads(f.read())
        i=1
        for obj in allJson:
            allActorsJson.append(populateActorsForMovie(obj["id"], authToken))
            print("{:d}. read movie_id = {:d}".format(i, obj["id"]))
            i+=1
            if (i%500 == 0):
                print("Taking a break...")
                time.sleep(5)
    with open("actors.json", "w") as f:
        f.write(json.dumps(allActorsJson))
        

def populateActorsForMovie(movie_id, authToken):
    headers = {
        "accept": "application/json",
        "Authorization": "Bearer {:s}".format(authToken)
    }
    getUrl = "https://api.themoviedb.org/3/movie/{:d}/credits?api_key={:s}".format(movie_id,authToken)
    res = requests.get(getUrl,headers)
    return res.json()
#607bd7d021d15492d56f9bc2242cee2e
            
        
        
    

if __name__=="__main__":
    #src=input("Source directory: ")
    #combineJsons(src)
    
    #changeImageBasePath("","https://image.tmdb.org/t/p/w1280")
    
    authToken = input()
    populateAllActors(authToken)
