def merge_sort(arreglo:list[int]):
    if len(arreglo) <= 1:
        return arreglo
    mitad = len(arreglo) // 2
    subarreglo_izq = merge_sort(arreglo[:mitad])
    subarreglo_der = merge_sort(arreglo[mitad:])
    return unir(subarreglo_izq, subarreglo_der)

def unir(subarreglo_izq:list[int], subarreglo_der:list[int]):
    res: list[int] = []
    i:int = 0
    j:int = 0    
    while i < len(subarreglo_izq) and j < len(subarreglo_der):
        if subarreglo_izq[i] <= subarreglo_der[j]:
            res.append(subarreglo_izq[i])
            i += 1
        else:
            res.append(subarreglo_der[j])
            j += 1
    while i < len(subarreglo_izq):
        res.append(subarreglo_izq[i])
        i += 1
    while j < len(subarreglo_der):
        res.append(subarreglo_der[j])
        j += 1
    return res