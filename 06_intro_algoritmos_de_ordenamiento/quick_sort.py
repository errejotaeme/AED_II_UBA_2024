def quick_sort(arreglo:list[int]):
    indice_izq:int = 0
    indice_der:int = len(arreglo)-1
    ordenar(arreglo, indice_izq, indice_der)

def ordenar(arreglo:list[int],indice_izq:int, indice_der:int):
    if indice_der <= indice_izq:
        return
    indice_pivote:int = particion(arreglo, indice_izq, indice_der)
    ordenar(arreglo, indice_izq, indice_pivote-1)
    ordenar(arreglo, indice_pivote+1, indice_der)    

def particion(arreglo:list[int], indice_izq:int, indice_der:int):
    i:int = indice_izq
    j:int = indice_der
    pivote:int = arreglo[indice_izq]
    while True:
        while arreglo[i] <= pivote:
            i += 1
            if i == indice_der:
                break
        while pivote <= arreglo[j]:
            j -= 1
            if j == indice_izq:
                break
        if i >= j:
            break
        arreglo[i], arreglo[j] = arreglo[j], arreglo[i]
    arreglo[indice_izq], arreglo[j] = arreglo[j], arreglo[indice_izq]
    return j
    
