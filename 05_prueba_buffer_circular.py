class Buffer:
    cola:list
    primero:int
    ultimo:int
    cantElem:int
    capacidad:int
    
    def __init__(self, cap:int):
        
        self.cola = [None] * cap
        self.primero = 0
        self.ultimo = 0
        self.cantElem = 0
        self.capacidad = cap

   
    def encolar(self, e):
        if(self.cantElem < self.capacidad):
            self.cola[self.ultimo] = e
            if(self.ultimo + 1 == self.capacidad):
                self.ultimo = 0
            else:
                self.ultimo = self.ultimo + 1
            self.cantElem = self.cantElem + 1

    def desencolar(self):
        res = None
        if(self.cantElem > 0):
            res = self.cola[self.primero]
            self.cola[self.primero] = None
            if(self.primero + 1 == self.capacidad):
                self.primero = 0
            else:
                self.primero = self.primero + 1
            self.cantElem = self.cantElem - 1
