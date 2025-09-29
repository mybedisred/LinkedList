/*
Author: Spencer Gilcrest
Date: 9/29/25
This class has methods lets you manipulate a linked list of Strings
*
Problem:  Write a program that keeps and manipulates a linked list of
	    String data. The data will be provided by the user one item at a time.
      The user should be able to do the following operations:
                     -add "String"
                                adds an item to your list (maintaining alphabetical order)
                     -remove "String"
                                if the item exists removes the first instance of it
                     -show
                                should display all items in the linked list
                     -clear
                               should clear the list
	Input:  commands listed above
	Output:  the results to the screen of each menu
	    choice, and error messages where appropriate.
*/
public class LinkedList{

  //instance varialbes go here (think about what you need to keep track of!)
  private ListNode head;
  private int listCount;

  //constructors go here
  public LinkedList(){
    head = null;
    listCount = 0;
  }


  //precondition: the list has been initialized
  //postcondition: the ListNode containing the appropriate value has been added and returned
  public ListNode addAValue(String line)
  {
    ListNode tempNode = new ListNode(line, null);
    ListNode currentNode = head;

    //if empty make the added node the head
    if (head == null){
      head = tempNode;
      listCount++;
      return tempNode;
    }

    //if value of added node comes before value of head in alphabet then replace head
    if (line.compareTo(head.getValue()) <= 0){
      tempNode.setNext(head);
      head = tempNode;
      listCount++;
      return tempNode;
    }

    //traverse list until you find where you where added node belongs
    while(currentNode.getNext() != null && line.compareTo(currentNode.getNext().getValue()) > 0){
      currentNode = currentNode.getNext();
    }

    //add the node where it is supposed to go
    tempNode.setNext(currentNode.getNext());
    currentNode.setNext(tempNode);
    listCount++;
    return tempNode;
  }


  //precondition: the list has been initialized
  //postcondition: the ListNode containing the appropriate value has been deleted and returned.
  //if the value is not in the list returns null
  public ListNode deleteAValue(String line)
  {
    //instead of exception return node with message if no head
    if (head == null){
      ListNode errorNode = new ListNode("this string is not in the list", null);
      return errorNode;
    }

    //if remove head replace head
    if (head.getValue().equals(line)){
      ListNode tempNode = head;
      head = head.getNext();
      tempNode.setNext(null);
      listCount--;
      return tempNode;
    }

    //remove node and connect loose ends
    ListNode currentNode = head;
    while (currentNode.getNext() != null){
      if (currentNode.getNext().getValue().equals(line)){
        ListNode tempNode = currentNode.getNext();
        currentNode.setNext(tempNode.getNext());
        tempNode.setNext(null);
        listCount--;
        return tempNode;
      }
      currentNode = currentNode.getNext();
    }

    //instead of exception return error node when value not found
    ListNode errorNode = new ListNode("this string is not in the list", null);
    return errorNode;
    
  }

  //precondition: the list has been initialized
  //postconditions: returns a string containing all values appended together with spaces between.
  public String showValues()
  {
    //tell if empty
    if (head == null){
      return "this list is empty";
    }

    String allNodes = "";
    ListNode currentNode = head;
    
    //add value of each node to allNodes until reach end of linkedlist
    while (currentNode != null){
      allNodes+=currentNode.getValue();
      if (currentNode.getNext() != null){
        allNodes += " ";
      }
      currentNode = currentNode.getNext();
    }
    return allNodes;
  }

  //precondition: the list has been initialized
  //postconditions: clears the list.
  public void clear()
  {
    head = null;
    listCount = 0;
  
  }

  /* 
   * precondition: the list has been intitialized
   * postcondition: the entire list has been reversed
   */
  public void reverse(){

    ListNode previousNode = null; 
    ListNode currentNode = head;
    ListNode nextNode = null;
    

    while (currentNode != null){
      
      nextNode = currentNode.getNext();
      currentNode.setNext(previousNode);
      previousNode = currentNode;
      currentNode = nextNode;
    }
    
    head = previousNode;
    
  }

  //preconidition: list has been initialized with atleast 1 element and n not greater than listCount
  //postcondition: reverses each chunk of n nodes, if there isn't enough elements to fit in a chunk they dont get reversed
  public void nReverse(int n){
    ListNode prevChunkLastNode = null;
    ListNode currentNode = head;
    int nodesLeft = listCount;

    //while thers enough nodes
    while (nodesLeft >= n){
      ListNode startOfChunk = currentNode;
      ListNode previousNode = null;
      ListNode nextNode = null;

      //does reversing 
      for (int i = 0; i < n; i++){
        nextNode = currentNode.getNext();
        currentNode.setNext(previousNode);
        previousNode = currentNode;
        currentNode = nextNode;
      }

      //on the first chunk there is no previous chunk so you have to reset head
      if (prevChunkLastNode == null){
        head = previousNode;
      }
      //on the rest of chunks just connect the end of the previous chunk to the start of current one
      else {
        prevChunkLastNode.setNext(previousNode);
      }

      //startOfChunk is really end of chunk after reversing so connect to next chunk and make it end of previous chunk
      startOfChunk.setNext(currentNode);
      prevChunkLastNode = startOfChunk;

      nodesLeft -= n;
    }
  }
}
