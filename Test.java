设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：

get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/design-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class MyLinkedList {
    private class Node{
        public int val;
        public Node next;
        public Node(int val){
            this.val=val;
        }
    }
    private Node head;
    int size;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index<0||index>=size){
            return -1;
        }
        Node cur=head;
        while(index!=0){
            cur=cur.next;
            index--;
        }
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node=new Node(val);
        if(head==null){
            head=node;
        }else{
            node.next=head;
            head=node;
        }
        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node=new Node(val);
        if(head==null){
            head=node;
        }else{
            Node cur=head;
            while(cur.next!=null){
                cur=cur.next;
            }
            cur.next=node;
        }
        size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index<0||index>size){
            return ;
        }
        if(index==0){
            addAtHead(val);
        }else{
            Node node=new Node(val);
            Node cur=head;
            while(index-1!=0){
                cur=cur.next;
                index--;
            }
            node.next=cur.next;
            cur.next=node;
        }
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index<0||index>=size){
            return;
        }
        if(index==0){
            head=head.next;
        }else{
            Node cur=head;
           while(index-1!=0){
            cur=cur.next;
            index--;
           }
            cur.next=cur.next.next;
        }
        
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
 
 
 给你一个 n 行 m 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。

每次「迁移」操作将会引发下述活动：

位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
位于 grid[i][m - 1] 的元素将会移动到 grid[i + 1][0]。
位于 grid[n - 1][m - 1] 的元素将会移动到 grid[0][0]。
请你返回 k 次迁移操作后最终得到的 二维网格。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shift-2d-grid
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> res=new ArrayList<>();
        int row=grid.length;
        int col=grid[0].length;
        for(int i=0;i<row;i++){
            List<Integer> Row=new ArrayList<>();
            for(int j=0;j<col;j++){
                Row.add(grid[i][j]);
            }
            res.add(Row);
        }
        k%=(row*col);
        for(int count=0;count<k;count++){
            List<Integer> tmp=new ArrayList<>();
            tmp.add(res.get(row-1).get(col-1));
            for(int i=0;i<row-1;i++){
                tmp.add(res.get(i).get(col-1));
            }
            for(int i=col-1;i>0;i--){
                for(int j=0;j<row;j++){
                    int pre=res.get(j).get(i-1);
                    res.get(j).set(i,pre);
                }
            }
            for(int i=0;i<row;i++){
                res.get(i).set(0,tmp.get(i));
            }
        }
        return res;
    }
}