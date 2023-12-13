# FIX: there has to be a better way to declare arrays with fixed sizes in python
class HashTable():

    def __init__(self, size: int) -> None:
        self.data = [None] * size
        self.size = size
    def __hash(self, key:str) -> int:
        hash = 0
        for char in key:
            hash += ord(char)
        return hash % self.size

    def set(self, key: str, value: int):
        idx= self.__hash(key)
        if not self.data[idx]:
            self.data[idx] = [[key,value]]
        else:
            self.data[idx] += [[key,value]]

    def get(self, key:str):
        idx = self.__hash(key)
        addr = self.data[idx]
        if addr is not None:
            for i in range(len(addr)):
                if addr[i][0] == key:
                    return addr[i][1]
        return None
    
    def keys(self):
        keys = []
        for i in range(self.size):
            if self.data[i] is not None:
                keys.append(self.data[i][0][0])
        return keys

    def print(self):
        return self.data

hash = HashTable(30)
hash.set("oi", 100)
hash.set("o1i", 300)
hash.set("oi2", 380)
hash.set("flw", 2000)
hash.set("flw2", 3000)
print(hash.keys())
