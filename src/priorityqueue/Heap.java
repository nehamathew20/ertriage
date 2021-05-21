package priorityqueue;
 

 
import java.util.Comparator;
 

 
public class Heap<T> implements PriorityQueueADT<T> {
 
 private int numElements;
 private T[] heap;
 private boolean isMaxHeap;
 private Comparator<T> comparator;
 private final static int INIT_SIZE = 5;
 

 /** 
  * Constructor for the heap.
  * @param comparator comparator object to define a sorting order for the heap elements.
  * @param isMaxHeap Flag to set if the heap should be a max heap or a min heap.
  */
 
 public Heap(Comparator<T> comparator, boolean isMaxHeap) {

    numElements = 0;

     heap = (T[]) new Object[INIT_SIZE];
     for (int i=0; i<heap.length;i++) {
       heap[i] = null;
     }
     this.isMaxHeap = isMaxHeap;
     this.comparator = comparator;
 
 }
 
 /**
  * This results in the entry at the specified index "bubbling up" to a location
  * such that the property of the heap are maintained. This method should run in
  * O(log(size)) time.
  * Note: When enqueue is called, an entry is placed at the next available index in 
  * the array and then this method is called on that index. 
  *
  * @param index the index to bubble up
  */
 
 public void bubbleUp(int index) {
 
  while (index > 0) {
    int parIndex = (index - 1) / 2;
      if (compare(heap[index],heap[parIndex]) <= 0) {
        return;
       }
       else {
         swap(index,parIndex);
         index = parIndex;
       }
     }
 }
 

 
 /**
 
  * This method results in the entry at the specified index "bubbling down" to a
  * location such that the property of the heap are maintained. This method
  * should run in O(log(size)) time.
  * Note: When remove is called, if there are elements remaining in this
  *  the bottom most element of the heap is placed at
  * the 0th index and bubbleDown(0) is called.
  * 
  * @param index
  */
 
 public void bubbleDown(int index) {
     int childIndex = 2 * index + 1;
 
     T val = heap[index];
     while(childIndex < size()) {
       T maxValue = val;
       int maxIndex = -1;
       for (int i=0; i<2 && (i + childIndex) < size(); i++) {
         if (compare(heap[i+childIndex],maxValue) > 0) {
           maxValue = heap[i + childIndex];
           maxIndex = i + childIndex;
         }
       }
       if (maxValue == val) {
         return;
       }
       else {
         swap(index,maxIndex);
         index = maxIndex;
         childIndex = 2 * index + 1;
       }
 
     }
 
 }
 

 
 /**
 
  * Test for if the queue is empty.
  * @return true if queue is empty, false otherwise.
  */
 
 public boolean isEmpty() {
 
   boolean isEmpty = false;
     if (size() == 0) {
       isEmpty = true;
     }
   return isEmpty;
 
 }
 
 /**
  * Number of data elements in the queue.
  * @return the size
  */
 
 public int size(){
   int size = -100;
    size = numElements;
     return size;
 }
 

 
 /**
 
  * Compare method to implement max/min heap behavior.  It calls the comparae method from the 
  * comparator object and multiply its output by 1 and -1 if max and min heap respectively.
  * TODO: implement the heap compare method
  * @param element1 first element to be compared
  * @param element2 second element to be compared
  * @return positive int if {@code element1 > element2}, 0 if {@code element1 == element2}, negative int otherwise
  */
 
 public int compare(T element1 , T element2) {
 
   int result = 0;
   int compSign =  -1;
 
   if (isMaxHeap) {
     compSign = 1;
   }
   result = compSign * comparator.compare(element1, element2);
   return result;
 
 }
 

 
 /**
  * Return the element with highest (or lowest if min heap) priority in the heap 
  * without removing the element.
  * @return T, the top element
  * @throws QueueUnderflowException if empty
  */
 
 public T peek() throws QueueUnderflowException {
 
    T data = null;
   if (isEmpty()) {
    throw new QueueUnderflowException();
   }
 
   data = heap[0];
   return data;
 
 }  
 

 
 /**
 
  * Removes and returns the element with highest (or lowest if min heap) priority in the heap.
  * @return T, the top element
  * @throws QueueUnderflowException if empty
  */
 
 public T dequeue() throws QueueUnderflowException{
 
   T data = null;
     if (isEmpty()) {
      throw new QueueUnderflowException();
     }
 
   data = heap[0];
 
   if (size() == 1) {
     heap[0] = null;
     numElements--;
   }
 
   else {
     swap(0,size()-1);
 
     T[] heap2 = (T[]) (new Object[size()-1]);
       for (int index = 0; index < size()-1; index++) {
        heap2[index] = heap[index];
       }
 
     heap = heap2;
     numElements--;
     bubbleDown(0);
   }
 
     return data;
 
 }
 

 
 /**
  * Enqueue the element.
  * @param the new element
  */
 
 public void enqueue(T newElement) {

     if (numElements >= INIT_SIZE) {
      expandCapacity();
     }
 
     if(isEmpty()) {
      heap[0] = newElement;
     }
     else {
      heap[size()] = newElement;
      bubbleUp(size());
     }
     numElements++;
 
 }
 

 
 private int getLeftChildOf(int parentIndex) {
   if (parentIndex < 0) {
     throw new IndexOutOfBoundsException();
   }
	  return 2 * parentIndex;
 
 }
 
 private int getRightChildOf(int parentIndex) {
   if (parentIndex < 0) {
     throw new IndexOutOfBoundsException();
   }
	  return 2*parentIndex+1;
 
 }

 private int getParentOf(int childIndex) {
	  return childIndex/2;
 }
 
 
 
 private void swap(int index1, int index2) {
	  T temp;
	  temp = heap[index1];
	  heap[index1] = heap[index2];
	  heap[index2] = temp;
 }
 

 
 private void expandCapacity() {
 
		T[] newHeap = (T[]) (new Object[heap.length + 1]);
	  int i;
			for (i = 0; i < heap.length; i++) {
      newHeap[i] = heap[i];
			}
		newHeap[i] = null;
		heap = newHeap;
 
	  }
 
}
