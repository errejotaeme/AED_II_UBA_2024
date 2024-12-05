def counting_sort(arreglo:list[int]):
    n:int = len(arreglo)
    k:int = max(arreglo)
    conteo:list[int] = [0] * (k+1)
    res:list[int] = [0] * n
    for h in range(n):
        conteo[arreglo[h]] += 1
    for i in range(1,k+1):
        conteo[i] += conteo[i-1]
    for j in range(n-1,-1,-1):
        res[conteo[arreglo[j]] - 1] = arreglo[j]
        conteo[arreglo[j]] -= 1        
    return res


    
    



