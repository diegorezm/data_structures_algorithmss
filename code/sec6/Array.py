from typing import Union

class Array():
    def __init__(self):
        self.length = 0
        self.data = {}

    def get(self,index: int):
        return self.data[index]

    def push(self,item: Union[int,str]):
        if not isinstance(item, (int, str)):
            raise TypeError("Item must be of type int or str")
        self.data[self.length] = item
        self.length += 1
        return self.length

    def pop(self) -> Union[int,str]:
        lastItem = self.data[self.length  - 1]
        del self.data[self.length - 1]
        self.length -= 1
        return lastItem

    def __shiftItems(self,index: int):
        for i in range(index, self.length - 1):
            self.data[i] = self.data[i + 1]
        del self.data[self.length - 1]
        self.length -= 1

    def remove(self,index: int) -> Union[int,str]:
        item = self.data[index]
        self.__shiftItems(index)
        return item


newArray = Array()

newArray.push("oi")
newArray.push(1)
newArray.push(2)
newArray.push(3)
newArray.push(4)
newArray.push(True)

print(newArray.get(5))
