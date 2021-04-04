

public class HeroNode {
	public int no;//英雄编号
	// public String name;//人名
	// public String nickname;//绰号
	public HeroNode next;//下一个节点
	public HeroNode(int no) {
		this.no = no;
		// this.name = name;
		// this.nickname = nickname;
    }
    







    
	@Override
	public String toString() {
		return "HeroNode no=" + no ;
	}

}
