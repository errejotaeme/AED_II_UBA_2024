def selection_sort(arreglo:list[int]):
    n = len(arreglo)
    for i in range(n):
        menor:int = i
        for j in range(i+1, n):
            if arreglo[j] < arreglo[menor]:
                menor = j
        arreglo[i], arreglo[menor] = arreglo[menor], arreglo[i]
