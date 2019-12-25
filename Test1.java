给定一个整数数组 A，只有我们可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。

形式上，如果我们可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum=0;
        for(int i=0;i<A.length;i++){
            sum+=A[i];
        }
        if(sum%3!=0){
            return false;
        }
        int avg=sum/3;
        int sum1=0;
        int sum2=0;
        int l=0;
        int r=A.length-1;
        while(l<=r){
           if(sum1!=avg){
               sum1+=A[l++];
           }
           if(sum2!=avg){
               sum2+=A[r--];
           }
           if(sum1==avg&&sum2==avg){
               break;
           }
       }
       return l<=r;
    }
}

在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。

如果小镇的法官真的存在，那么：

小镇的法官不相信任何人。
每个人（除了小镇法官外）都信任小镇的法官。
只有一个人同时满足属性 1 和属性 2 。
给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。

如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-the-town-judge
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int findJudge(int N, int[][] trust) {
        for(int i=1;i<=N;i++){
            int num=0;
            int flag=0;
            for(int j=0;j<trust.length;j++){
                if(trust[j][0]==i){
                    flag=1;
                    break;
                }
                if(trust[j][1]==i){
                    num++;
                }
            }
            if(flag==0&&num==N-1){
                return i;
            }
        }
        return -1;
    }
}

给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度 和 直系下属的id。

比如，员工1是员工2的领导，员工2是员工3的领导。他们相应的重要度为15, 10, 5。那么员工1的数据结构是[1, 15, [2]]，员工2的数据结构是[2, 10, [3]]，员工3的数据结构是[3, 5, []]。注意虽然员工3也是员工1的一个下属，但是由于并不是直系下属，因此没有体现在员工1的数据结构中。

现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/employee-importance
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        int sum=0;
        for(Employee e:employees){
            if(e.id==id){
                sum+=e.importance;
                if(e.subordinates.size()==0){
                    return e.importance;
                }
                for(int x:e.subordinates){
                    sum+=getImportance(employees,x);
                }
                return sum;
            }
        }
        return 0;
    }
}