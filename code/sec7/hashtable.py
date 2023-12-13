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
            self.data[idx] = [key,value]
        else:
            self.data[idx] += [[key,value]]
        print(self.data)

    def get(self, key:str):
        idx = self.__hash(key)
        return self.data[idx][1]

hash = HashTable(2)
hash.set("oi", 100)
hash.set("oi2", 100)
hash.set("flw", 200)
print(hash.get("flw"))

