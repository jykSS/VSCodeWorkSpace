
class SingleLinkedListDemo{
	//创建一个头结点，初始化数据，头结点不要动，不放具体的数据
 
	private HeroNode head = new HeroNode(0);
	public HeroNode getHead() {
		return head;
	}
	//添加节点
	public void add(HeroNode node) {
		//先找出最后的一个节点，把新加的节点放在最后一个节点的后面
		HeroNode temp = head;
		while(true) {
			if(temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = node;
	}
	public void addByOrder(HeroNode node) {
		HeroNode temp = head;
		boolean flag = false;
		while(true) {
			if(temp.next == null) {
				break;
			}
			if(temp.next.no>node.no) {
				break;
			}else if(temp.next.no == node.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			System.out.println("编号"+node.no+"已经存在了！");
		}else {
			node.next = temp.next;
			temp.next = node;
		}
	}
	
	public void list() {
		HeroNode temp = head;
		if(temp.next == null) {
			System.out.println("链表为空！");
			return;
		}
		while(true) {
			if(temp.next == null) {
				break;
			}
			System.out.println(temp.next);
			temp = temp.next;
		}
    }
    public void put(HeroNode node){
        HeroNode temp = head;
        while(true){
            if(null==temp.next){
                break;
            }
            temp=temp.next;
        }
        temp.next=node;
    }
}

