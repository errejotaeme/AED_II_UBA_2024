def ajustar(arreglo:list[int], n:int, i:int):
    id_maximo:int = i 
    id_hijo_izq:int = 2*i + 1     
    id_hijo_der:int = 2*i + 2     
    if id_hijo_izq < n and arreglo[id_maximo] < arreglo[id_hijo_izq]:
        id_maximo = id_hijo_izq        
    if id_hijo_der < n and arreglo[id_maximo] < arreglo[id_hijo_der]:
        id_maximo = id_hijo_der        
    if id_maximo != i:
        arreglo[i], arreglo[id_maximo] = arreglo[id_maximo], arreglo[i]
        ajustar(arreglo, n, id_maximo)

def heap_sort(arreglo):
    n = len(arreglo)
    for i in range(n//2 - 1, -1, -1):
        ajustar(arreglo, n, i)  
    for i in range(n-1, 0, -1):
        arreglo[i], arreglo[0] = arreglo[0], arreglo[i] 
        ajustar(arreglo, i, 0)