def insertion_sort(arreglo: list[int]):
    n:int = len(arreglo)
    for i in range(1, n):
        clave:int = arreglo[i]
        j:int = i - 1      
        while (j >= 0 and arreglo[j] > clave):
            arreglo[j+1] = arreglo[j] 
            j -= 1
        arreglo[j+1] = clave    
