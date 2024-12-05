def bubble_sort(arreglo:list[int]):
    n:int = len(arreglo)
    for i in range(n):
        for j in range(n-1,i,-1):
            if arreglo[j] < arreglo[j-1]:
                arreglo[j], arreglo[j-1]  = arreglo[j-1], arreglo[j]
