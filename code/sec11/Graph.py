class Graph():
    def __init__(self) -> None:
        self.size = 0
        self.list = {}

    def addVertex(self,node:str) -> dict:
        self.list[node] = []
        self.size += 1
        return self.list

    def addEdge(self, firstNode:str, SecondNode:str) -> dict:
        self.list[firstNode].append(SecondNode)
        self.list[SecondNode].append(firstNode)
        return self.list

    def showConnections(self) -> str:
        nodes = self.list
        string = ""
        for node in nodes:
            nodeConnections = self.list[node]
            connections = ""
            vertex = ""
            for vertex in nodeConnections:
                connections += vertex + " "
            string += node + "-->" + connections + " \n"
        return string
            

    def __repr__(self) -> str:
        return str(self.list)

graph = Graph()
graph.addVertex('0')
graph.addVertex('1')
graph.addVertex('2')
graph.addVertex('3')
graph.addVertex('4')
graph.addVertex('5')
graph.addVertex('6')
graph.addEdge('3', '1') 
graph.addEdge('3', '4') 
graph.addEdge('4', '2') 
graph.addEdge('4', '5') 
graph.addEdge('1', '2') 
graph.addEdge('1', '0') 
graph.addEdge('0', '2') 
graph.addEdge('6', '5') 
print(graph.showConnections())
