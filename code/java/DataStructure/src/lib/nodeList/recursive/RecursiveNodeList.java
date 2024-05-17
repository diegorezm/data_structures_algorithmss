package lib.nodeList.recursive;

public class RecursiveNodeList {
    private RecursiveNode head;

    public RecursiveNodeList() {
        this.head = null;
    }
    public void push(int val){
        if(this.head == null) {
            this.head = new RecursiveNode(val);
        }else {
            this.head.push(val);
        }
    }

    public Integer pop(){
        if(this.head != null){
            var aux = this.head.getVal();
            if(this.head.getNext() != null){
                this.head = this.head.getNext();
            }else {
                this.head = null;
            }
            return aux;
        }
        return null;
    }

    public void show(){
        if(this.head == null) return;
        this.head.show();
    }
    public int sum(){
        if(this.head != null){
            return this.head.sum();
        }
        return 0;
    }
    public int sumOdd(){
        if(this.head != null){
            return this.head.sumOdd();
        }
        return 0;
    }
    public int max(){
        if(this.head != null){
            return this.head.max();
        }
        return 0;
    }
    public boolean find(int val){
        if(this.head != null){
            return this.head.find(val);
        }
        return false;
    }
    public int size(){
        if(this.head != null){
            return this.head.size();
        }
        return 0;
    }
    public void printEven(){
        if(this.head != null){
            this.head.printEven();
        }
    }

}
