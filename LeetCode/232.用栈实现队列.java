/*
 * @lc app=leetcode.cn id=232 lang=java
 *
 * [232] 用栈实现队列
 */

// @lc code=start
class MyQueue {
    Deque<Integer> stIn;
    Deque<Integer> stOut;

    public MyQueue() {
        stIn = new LinkedList<>();
        stOut = new LinkedList<>();
    }

    public void push(int x) {
        stIn.addLast(x);
    }

    public int pop() {
        peek();
        return stOut.removeLast();
    }

    public int peek() {
        // 只有当stOut为空的时候，再从stIn里导入数据（导入stIn全部数据）
        if (stOut.isEmpty()) {
            // 从stIn导入数据直到stIn为空
            while (!stIn.isEmpty()) {
                stOut.addLast(stIn.removeLast());
            }
        }
        return stOut.peekLast();
    }

    public boolean empty() {
        return stIn.isEmpty() && stOut.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
// @lc code=end
