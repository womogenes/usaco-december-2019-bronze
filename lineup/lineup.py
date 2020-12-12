from functools import lru_cache

with open("lineup.in") as fin:
    data = fin.read().split("\n")[:-1]
    
cows = [
    "Bessie",
    "Buttercup",
    "Belinda",
    "Beatrice",
    "Bella",
    "Blue",
    "Betsy",
    "Sue"
]
cows.sort()

# Parse the input into a list of pairs
n = int(data[0])
constraints = []
for i in range(1, n + 1):
    words = data[i].split(" ")
    pair = [cows.index(words[0]), cows.index(words[-1])]
    
    constraints.append(pair)
    
    
def permutations(nums):
    # Generate all permutations of nums
    n = len(nums)
    if n == 1:
        return [nums]
        
    results = []
    for start in range(n):
        for p in permutations(nums[:start] + nums[start + 1:]):
            perm = [nums[start]] + p
            results.append(perm)
            
    return results


for p in permutations(list(range(8))):
    works = True
    for cow1, cow2 in constraints:
        if abs(p.index(cow1) - p.index(cow2)) != 1:
            works = False
            break
            
    if works:
        # Convert list of integers to list of cow names
        ans = [cows[i] for i in p]
        break
        
with open("lineup.out", "w") as fout:
    fout.write("\n".join(ans) + "\n")