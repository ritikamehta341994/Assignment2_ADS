import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
	public static TreeEncapsulation treeEncapsulation = null;
	
	public static void main(String [] args) throws IOException {
		
		System.out.println("\n"+"**************************************************************************************************************");
		System.out.println("\n"+"\t\t\t\tInsert Data into SPLAY and BST\t\t\t\t\t\t\t\t\t\t");
		System.out.println("\n"+"**************************************************************************************************************");

		//Insert data for random 2 zipcodes into BST and SPLAY BST
		treeEncapsulation = treeOperations(1,false);
		System.out.println("\n"+"**************************************************************************************************************");
		
		//Display the SPLAY tree
		System.out.println("\n"+"\t\t\t\tSPLAY");
		System.out.println("\n"+"**************************************************************************************************************");
		treeEncapsulation.getStree().printTree();
		System.out.println("\n"+"**************************************************************************************************************");

		//Display the Binary search tree
		System.out.println("\n"+"\t\t\t\tBST");
		System.out.println("\n"+"**************************************************************************************************************");
		treeEncapsulation.getBtree().inorder();
		System.out.println("\n"+"**************************************************************************************************************");
		
		int operation = 0;
		
		
		while(operation != 7) {
			System.out.println("\n"+"--------------------------------------------------------------------------------------------------------------");
			//Operations 2-6 are performed on the tree returned from above
			System.out.println("Enter the operation number you want to perform from the list below : \n1.\tExperimental Analysis"
					+ "\n2.\tSearch Crime Incidents in Zip Code - SPLAY "
					+ "\n3.\tSearch Crime Incidents in Zip Code - BST"
					+ "\n4.\tDelete Node for input Zip Code - BST"
					+ "\n5.\tDisplay - SPLAY"
					+ "\n6.\tDisplay - BST"
					+"\n7.End\n");
			BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
			operation = Integer.parseInt(reader.readLine());
			switch(operation) {
			
			case 1:System.out.println("\n"+"**************************************************************************************************************");
				   System.out.println("\n"+"\t\t\t\t\t\tEXPERIMENTAL ANALYSIS\t\t\t\t\t\t\t\t\t\t");
				   System.out.println("\n"+"**************************************************************************************************************");
				   treeOperations(5, true);
				   System.out.println("\n"+"**************************************************************************************************************");
				   break;
			
			case 2:System.out.println("\nEnter Zip Code you want to search for in SPLAY: ");
				   int zipCase2  = Integer.parseInt(reader.readLine());
				   LinkedSplayBinaryTree.Node searcedNodeSplay = treeEncapsulation.getStree().search(zipCase2); //Search the node with the input zipcode in SPLAY tree
				   if(searcedNodeSplay != null) {
			    	   System.out.println("\nNode Found !!");
			    	   //Display the zipcode and the count of crime incidents in the zipcode
			    	   System.out.println("\nZip Code: "+searcedNodeSplay.getElement().getZipCode()+" Number of Crime Incidents: "+searcedNodeSplay.getElement().getListCrimeIncidents().size());
			       }
			       else {
			    	   System.out.println("\nNode Not Found !!");   
			       }
				   break;
				   
			case 3:System.out.println("\nEnter Zip Code you want to search for in BST: ");
			       int zipCase3  = Integer.parseInt(reader.readLine());
			       BinarySearchTree.Node searchedNodeBst = treeEncapsulation.getBtree().search(treeEncapsulation.getBtree().root,zipCase3);//Search the node with the input zipcode in BS tree
			       if(searchedNodeBst != null) {
			    	   System.out.println("\nNode Found !!");
			    	  //Display the zipcode and the count of crime incidents in the zipcode
			    	   System.out.println("\nZip Code: "+searchedNodeBst.key.getZipCode()+" Number of Crime Incidents: "+searchedNodeBst.key.getListCrimeIncidents().size());
			       }
			       else {
			    	   System.out.println("\nNode Not Found !!");   
			       }
			       break;
			       
			case 4:System.out.println("\nEnter Zip Code you want to delete in BST : ");
		   	   		int zipCase4  = Integer.parseInt(reader.readLine());
		   	   		treeEncapsulation.getBtree().deleteKey(zipCase4); // Delete the node with the entered zipcode from the BST
		   	   		System.out.println("\nEnter 6 to view the new BST ");
		   	   		break;
		   	   		
			case 5:System.out.println("\n"+"\t\t\t\tSPLAY");
				   System.out.println("\n"+"**************************************************************************************************************");
				   treeEncapsulation.getStree().printTree(); // Display the SPLAY tree
				   break;
					 
			case 6: System.out.println("\n"+"\t\t\t\tBST");
					System.out.println("\n"+"**************************************************************************************************************");
					treeEncapsulation.getBtree().inorder(); // Display the Binary Tree
			 		break;
			 
			case 7:System.out.println("Process Completed, System Exiting!");
				   System.exit(0); // Exit the system
			 	   break;
			
			default : System.out.println("Invalid operation");
					  break;
			}
			
		}
  }

	/**
	 * 
	 */
	private static TreeEncapsulation treeOperations(int counter,boolean isExperiment) {

		LinkedSplayBinaryTree tree = new LinkedSplayBinaryTree();
		BinarySearchTree bTree = new BinarySearchTree();
		//create an object of the class which stores the SPLAY and BST , returns it for later search and delete operations
		TreeEncapsulation result = new TreeEncapsulation(bTree, tree);
		
		FileManager fileManager = new FileManager();
		//Fetch the data from the input csv file of crime incidents
		LinkedList<CrimeIncidents> crimeIncidents = fileManager.getCrimeData();

		//Initialise the list with lists(increasing in size) of random zipcodes 
		List<List<Integer>> zipCodesList = List.of(
                List.of(12,5),
                List.of(4,3,1),
                List.of(7,9,13,19,18),
                List.of(21,22,23,11,2,10),
                List.of(6,8,14,17,15,16,20)
            );
		
		

		long timeToSearchInSplay = 0;
		long timeToSearchInBst = 0;
	
		int nodeSize = 0;
		
		System.out.println("Node Size\t|\tTotal Time to Search and Update SPLAY\t|\tTotal Time to Search and Update BST");
		
		//Counter denotes how many lists of the zipcodes need to be processed
		//If it is 1 only 1st list of zip code is processed
		//If 5 all the lists of zip code is processed
		for(int i = 0;i<counter;i++) {
			//Keep count of the total number of zipcodes
			nodeSize+=zipCodesList.get(i).size();
			for(int j = 0;j<zipCodesList.get(i).size();j++) {
				//Traverse each of the zip code 
				int zip = zipCodesList.get(i).get(j);
				for(CrimeIncidents c : crimeIncidents) {
					//If the crime incident row we are processing has the zip code we are looking for
					//Then search if a node is present in SPLAY and BST  for the zip code
					//If node is not present add the new node else fetch the existing node and to its list of
					//crime incidents add the crime incident
					if(c.getZipCode() == zip) {
						
						if(tree.search(zip) == null){
							
							LinkedList<CrimeIncidents> tempListSplay = new LinkedList<CrimeIncidents>();
							tempListSplay.add(c); // add to the node's data
							NodeElement newNodeSplay = new NodeElement(tempListSplay,zip);
							tree.insertNewNode(newNodeSplay); // add node to the SPLAY
						}
						else {
							
							long startTimeForSearchInSplay =  System.currentTimeMillis();
							tree.search(zip).getElement().getListCrimeIncidents().add(c); // get the list of crime incidents for the zip code and add the new crime incident to the list
							long endTimeForSearchInSplay =  System.currentTimeMillis();
							timeToSearchInSplay += endTimeForSearchInSplay - startTimeForSearchInSplay;
						}
						if(bTree.search(bTree.root,zip) == null) {
							
							LinkedList<CrimeIncidents> tempListBst = new LinkedList<CrimeIncidents>();
							tempListBst.add(c);//add to the node's data
							NodeElement newNodeBst = new NodeElement(tempListBst,zip);
							bTree.insert(newNodeBst);// add node to the BST
						}
						
						else {
				
							long startTimeForSearchInBst =  System.currentTimeMillis();
							bTree.search(bTree.root,zip).key.getListCrimeIncidents().add(c);// get the list of crime incidents for the zip code and add the new crime incident to the list
							long endTimeForSearchInBst=  System.currentTimeMillis();
							timeToSearchInBst += endTimeForSearchInBst - startTimeForSearchInBst;
						}
					}
				}
				

			}
			
			
			System.out.println(nodeSize+"\t\t|\t"+(double)(timeToSearchInSplay)+"\t\t\t\t\t|\t"+(double)(timeToSearchInBst));
					
		}

		result.setBtree(bTree); // set updated BST
		result.setStree(tree); // set updated SPLAY tree
	
		return result;
	}
}
