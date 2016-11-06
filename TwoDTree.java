
/**
* Advanced recursive algorithm implementation for traversing/manipulating
* a 2-D Binary Search Tree (BST)
* @author Jason Schenck
* A very basic nested class encapsulating a node in the TwoDTree provided
**/	
import java.util.*;

public class TwoDTree {
	/*************
	 * attributes
	 ************/

	TwoDTreeNode root;
        // Need a class level attribute, found flag for contains method
        //Set to false by default
        boolean containsFound = false;

	/***************
	 * constructor
	 **************/
	TwoDTree() {
		root = null;
	}

	/**********
	 * methods
	 *********/
	/**
	 * Adds a new node with the given x and y coordinates to the TwoDTree
	 * 
	 * @param x
	 * @param y
	 */
	public void add(int x, int y) {
		//Add method which calls helper addRecursion to add a new item to tree
		
                // create a new node to be added with input coordinates
                TwoDTreeNode nodeToAdd = new TwoDTreeNode(x,y);
                
                // create the level variable for alternating even/odd levels
                //Always start at 1 to ensure proper even/odd alternating with modulo
                int level = 1;
                
                // recursively search for insertion point starting at root
		root = addRecursion(nodeToAdd, root, level);           
	}

	public TwoDTreeNode addRecursion(TwoDTreeNode nodeToAdd, TwoDTreeNode node, int level) {
		// 5 Cases for recursion:
                // 1) current node is null, add new node there
                // 2) odd level, x1 < x2 --> branch left
                // 3) odd level, x1 > x2 --> branch right
                // 4) even level, y1 < y2 --> branch left
                // 5) even level, y1 > y2 --> branch right
               
                // find insertion point
		if (node == null) {
			// insertion point found; create new node
			node = nodeToAdd;
		} else if (level % 2 != 0 && nodeToAdd.xCoordinate < node.xCoordinate) {
			// level is odd, x1 < x2 , branch left                                                          
			
                        // moved down a level, increase level counter
                        level++;
                        node.left = addRecursion(nodeToAdd, node.left, level);
                        
                        
		} else if (level % 2 != 0 && nodeToAdd.xCoordinate > node.xCoordinate) {
			// level is odd, x1 > x2, branch right
			
                        // moved down a level, increase level counter
                        level++;
                        node.right = addRecursion(nodeToAdd, node.right,level);
                        
		} else if (level % 2 == 0 && nodeToAdd.yCoordinate < node.yCoordinate) {
                        // level is even, y1 < y2 , branch left 
                        
                        // moved down a level, increase level counter
                        level++;
                        node.left = addRecursion(nodeToAdd, node.left, level);
                        
                } else if(level % 2 == 0 && nodeToAdd.yCoordinate > node.yCoordinate)  {
                        // level is even, y1 > y2, branch right
                        
                        // moved down a level, increase level counter
                        level++;
                        node.right = addRecursion(nodeToAdd, node.right, level);
                    
                    
                }else {
			// Node already exists in this BST
		    System.out.println();	
                    System.out.println("*DUPLICATE ITEM* : That node already exists in the BST!");
                    System.out.println();
                    
                    
                    throw new IllegalArgumentException("Cannot add duplicate item to the tree!");
		}

		// return node (at end of recursion, will always return root node)
		return node;
	}        

	/**
	 * 
	 * @param x
	 * @param y
	 * @returns true if a node with the given x and y coordinates exist in the
	 *          tree.
	 */
	public boolean contains(int x, int y) {
		//contains method which calls helper containsRecursion to search the tree
            
                // create a new node with given params
                TwoDTreeNode nodeToFind = new TwoDTreeNode(x,y);
                
                //create the level variable for alternating even/odd levels
                int level = 1;
                
                //Recursively traverse the BST to find given node, alternating even/odd levels
                root = containsRecursion(nodeToFind, root, level);
                
                // If containsFound = true, then node FOUND
                // Otherwise, node not in BST
                if(containsFound){
                    return true;
                }
                else{
                    return false;
                }

                
                
                
	}
        
        public TwoDTreeNode containsRecursion(TwoDTreeNode nodeToFind, TwoDTreeNode node, int level){
		// 5 Cases for recursion:
                // 1) current node is nodeToFind --> set found = true, return root, break 
                // 2) odd level, x1 < x2 --> branch left
                // 3) odd level, x1 > x2 --> branch right
                // 4) even level, y1 < y2 --> branch left
                // 5) even level, y1 > y2 --> branch right
               
             // ensure containsFound boolean is reset to false prior to recursion logic
             containsFound = false;
		
            
              if (node != null) {
                                                                                     
                  if((nodeToFind.xCoordinate == node.xCoordinate) && (nodeToFind.yCoordinate == node.yCoordinate)){
                      // Current node = target node
                      // Node found --> update found boolean flag
                      
                      containsFound = true;
                      
                      return node;
                                                           
                      
                  } else if (level % 2 != 0 && nodeToFind.xCoordinate < node.xCoordinate) {
			// level is odd, x1 < x2 , branch left                                                          
			level++;
                        node.left = containsRecursion(nodeToFind, node.left, level);
                        
                        
		} else if (level % 2 != 0 && nodeToFind.xCoordinate > node.xCoordinate) {
			// level is odd, x1 > x2, branch right
			level++;
                        node.right = containsRecursion(nodeToFind, node.right,level);
                        
		} else if (level % 2 == 0 && nodeToFind.yCoordinate < node.yCoordinate) {
                        // level is even, y1 < y2 , branch left 
                        level++;
                        node.left = containsRecursion(nodeToFind, node.left, level);
                        
                } else if(level % 2 == 0 && nodeToFind.yCoordinate > node.yCoordinate)  {
                        // level is even, y1 > y2, branch right
                        level++;
                        node.right = containsRecursion(nodeToFind, node.right, level);
                    
                    
                }
                        
		}

		// return node
		
                return node;            
        }

	/**
	 * A method which prints a level order traversal of the tree
	 */
	public void levelOrderPrint() {
		Queue<TwoDTreeNode> queue = new LinkedList<TwoDTreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TwoDTreeNode node = queue.poll();
			System.out.print("(" + node.xCoordinate + "," + node.yCoordinate + ")");
			if (node.left != null)
				queue.add(node.left);
			if (node.right != null)
				queue.add(node.right);
		}
		System.out.println();
	}
        public int getMinX(){
           // create a node for the root node to begin
            TwoDTreeNode node = root;
            int minX = 0;
            boolean wentLeft = false;
            boolean wentRight = false;
  
            
            // first ensure left subtree exists from root    
            if (node.left != null){
                // branch all the way left to get the leftmost node
                while(node.left != null){
                    node = node.left;
                    wentLeft = true;                  
                }
                // after loop store the x value of the left most node as min
                minX = node.xCoordinate;
            }
            
            // if no left branching, go right

                if (node.left == null && node.right != null){
                    
                    node = node.right;
                    wentRight = true;
                    
                    if (node.left != null){
                        //branch left most in right sub tree
                        while (node.left != null){
                            node = node.left;
                        }
                       // after loop store x value for left most node of right sub tree as min
                        minX = node.xCoordinate;
                    }
                }
                
                if (wentLeft){
                // create a queue with left subtree of nodes to compare
                Queue<TwoDTreeNode> queueLeft = new LinkedList<TwoDTreeNode>();
		// Start queue at root's left child
                queueLeft.add(root.left);
		// store the node 
                TwoDTreeNode nodeLeft = queueLeft.peek();
                
                // using code from level-order traversal, store all nodes from left subtree in queue
                while (nodeLeft.left != null && nodeLeft.right != null) {
			
			if (nodeLeft.left != null){
				queueLeft.add(nodeLeft.left);
                        }
			if (nodeLeft.right != null){
				queueLeft.add(nodeLeft.right);   
                        }
                }
                // Go through every node in the queue and check for minX compare to existing minX, update
                // as neccesary
                while (!queueLeft.isEmpty()){
                    nodeLeft = queueLeft.remove();
                    if (minX > nodeLeft.xCoordinate){
                        minX = nodeLeft.xCoordinate;
                    }
                }
                
                // minX should contain min value of X, return it.
                return minX ;           
            
        }
                // Repeat same process for right sub tree
                if (wentRight){
                    // create a queue with left subtree of nodes to compare
                Queue<TwoDTreeNode> queueRight = new LinkedList<TwoDTreeNode>();
		queueRight.add(root.right);
		TwoDTreeNode nodeRight = queueRight.peek();
                while (nodeRight.left != null && nodeRight.right != null) {
			
			if (nodeRight.left != null){
				queueRight.add(nodeRight.left);
                        }
			if (nodeRight.right != null){
				queueRight.add(nodeRight.right);   
                        }
                }
                
                while (!queueRight.isEmpty()){
                    nodeRight = queueRight.remove();
                    if (minX > nodeRight.xCoordinate){
                        minX = nodeRight.xCoordinate;
                    }
                }
                
                // minX should contain min value of X, return it.
                return minX ;           
                }
                return -1;  // should never be reached... hopefully.
        }
        

	/**
	 * Nested class defining the node objects
	 * @author esahe2
	 *A nested class encapsulating a node in the TwoDTree
	 */
	private static class TwoDTreeNode {
		/*************
		 * attributes
		 ************/
		int xCoordinate;
		int yCoordinate;
		TwoDTreeNode right;
		TwoDTreeNode left;

		/***************
		 * constructors
		 **************/
		TwoDTreeNode(int x, int y) {
			xCoordinate = x;
			yCoordinate = y;
		}

		TwoDTreeNode(int x, int y, TwoDTreeNode leftChild, TwoDTreeNode rightChild) {
			xCoordinate = x;
			yCoordinate = y;
			left = leftChild;
			right = rightChild;
		}
	}

}