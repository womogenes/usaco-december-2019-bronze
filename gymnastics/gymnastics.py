with open("gymnastics.in") as fin:
    data = fin.read().split("\n")[:-1]
    
k, n = [int(i) for i in data[0].split(" ")]

# This is our answer
ans = 0

# Go through all possible pairs of cows (a, b)
for a in range(1, n + 1):
    for b in range(1, n + 1):
        if a == b: continue
            
        # Determine if a did better than b in all sessions
        better = True
        for session in data[1:]:
            rankings = [int(i) for i in session.split(" ")] # Convert the line to an int list
            
            if rankings.index(a) > rankings.index(b): # This is true when b did better than a
                better = False
                break
        
        ans += better
        
with open("gymnastics.out", "w") as fout:
    fout.write(str(ans) + "\n")