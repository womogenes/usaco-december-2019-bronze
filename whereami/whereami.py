with open("whereami.in") as fin:
    data = fin.read().split("\n")
    
n = int(data[0])
mailboxes = data[1]

def works(k):
    # Returns whether every subsequence of k consecutive mailboxes is unique
    sequences = set()
    
    for i in range(n - k + 1):
        subsequence = mailboxes[i:i + k]
        
        if subsequence in sequences:
            # This is not unique!
            return False
            
        sequences.add(subsequence)
            
    return True
    
    
for k in range(1, n + 1):
    if works(k):
        ans = k
        break
    
with open("whereami.out", "w") as fout:
    fout.write(str(ans) + "\n")