package lib.nodeList.recursive;

public class RecursiveNode {
    private int val;
    private RecursiveNode next;

    public RecursiveNode(int val) {
        this.val = val;
        this.next = null;
    }

    public RecursiveNode(int val, RecursiveNode node) {
        this.val = val;
        this.next = node;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public RecursiveNode getNext() {
        return next;
    }

    public void setNext(RecursiveNode next) {
        this.next = next;
    }

    public void push(int val){
        if(this.next == null){
            this.next = new RecursiveNode(val);
        }
        else {
            this.next.push(val);
        }
    }

    public void show(){
        System.out.print(this.val + " -> ");
        if(this.next != null){
            this.next.show();
        }
    }

    public int sum(){
        if(this.next == null){
            return this.val;
        }
        return this.val + this.next.sum();
    }

    public int sumOdd(){
        if(this.next == null){
            return this.val;
        }
        if(this.val % 2 == 0){
            return this.next.sumOdd();
        }
        return this.val + this.next.sumOdd();
    }
    public int max(){
        if(this.next == null){
            return this.val;
        }
        if(this.val > this.next.max()){
            return this.val;
        }
        return this.next.max();
    }
    public boolean find(int val){
       if(this.val == val){
           return true;
       }
       if(this.next != null){
           return this.next.find(val);
       }
       return false;
    }
    public int lastElement(){
        if(this.next == null){
            return this.val;
        }
        return this.next.lastElement();
    }
    public int size(){
        if(this.next == null){
            return 1;
        }
        return 1 + this.next.size();
    }

    public void printEven(){
       var aux = this.val % 2 == 0 ? this.val : null;
       if(aux != null){
           System.out.print(aux + " -> ");
       }
       if(this.next != null){
           this.next.printEven();
       }
    }
}
