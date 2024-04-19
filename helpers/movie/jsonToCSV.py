import pandas as pd
ip = input("input file: ")
op = input("output file: ")
with open(ip, encoding='utf-8') as inputfile:
    df = pd.read_json(inputfile)

df.to_csv(op, encoding='utf-8', index=False)