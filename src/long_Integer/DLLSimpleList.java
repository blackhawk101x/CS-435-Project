package long_Integer;

public class DLLSimpleList implements SimpleList<DLLNode>{

	private DLLNode head;
    private DLLNode tail;
    private int size;

    /**
     * Initializes the linked list
     */
    public DLLSimpleList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

	@Override
	public void insertFirst(int data) {
		DLLNode tmp= new DLLNode(data);
		if(head==null){ // if the list in empty
			this.head = tmp;
			this.tail = this.head;
		}
		else{
			this.head.setPrev(tmp);
			tmp.setNext(this.head);
			this.head = tmp;
		}
		this.size++;
	}

	@Override
	public void insertLast(int data) {
		DLLNode tmp = new DLLNode(data);
		if(this.tail == null){ // if the list is empty
			this.tail = tmp;
			this.head = this.tail;
		}
		else{
			this.tail.setNext(tmp);
			tmp.setPrev(this.tail);
			this.tail= tmp;
		}
		this.size++;
		
	}

	@Override
	public DLLNode first() {
		// TODO Auto-generated method stub
		return this.head;
	}

	@Override
	public DLLNode last() {
		// TODO Auto-generated method stub
		return this.tail;
	}

	@Override
	public boolean isFirst(DLLNode n) {
		// TODO Auto-generated method stub
		return (n.getPrev()==null && n.getData()==this.head.getData());
	}

	@Override
	public boolean isLast(DLLNode n) {
		// TODO Auto-generated method stub
		return (n.getNext()==null && n.getData()==this.tail.getData());
	}

	@Override
	public DLLNode before(DLLNode n) {
		// TODO Auto-generated method stub
		return n.getPrev();
	}

	@Override
	public DLLNode after(DLLNode n) {
		// TODO Auto-generated method stub
		return n.getNext();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.size==0 && this.head==null && this.tail==null);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

    
}
