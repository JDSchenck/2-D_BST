
/**
* Advanced recursive algorithm implementation for traversing/manipulating
* a 2-D Binary Search Tree (BST)
* @author Jason Schenck
* A very basic nested class encapsulating a node in the TwoDTree provided
**/	
public class Test2DBST {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
        //Test harness for 2D Binary Search Tree
        
        //FIRST TEST CASE: PERFECT BALANCED BINARY TREE

	System.out.println("building tree.");
	TwoDTree tDtree = new TwoDTree();
	tDtree.add(1,40);
	tDtree.add(40,70);
	tDtree.add(80,20);
	tDtree.add(10,10);
	tDtree.add(60,8);
        System.out.println("Min X value: " + tDtree.getMinX());


        System.out.println();
	System.out.println("level order traversal for this tree is:");
	tDtree.levelOrderPrint();
        System.out.println();
	
    }
    
}
