public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");

        String ss = "cfps-imp:7740;cfps-fee:7750;cfps-flight:7760;cfps-message:7770;cfps-warehouse:7780;cfps-custom:7790;cfps-awbchange:7690;cfps-abn:7680;cfps-selfservice:7670;cfps-bzcheck:7660;cfps-transfer:7650;cfps-awb:7640;cfps-extend:7630;cfps-openapi:7610;cfps-ws:7600;ctms-base:6030;ctms-guide:6020;cfps-scale:6670;cfps-monitor:5001;cfps-jobs:5002;xxl-job-admin:9080";
        String[] sss = ss.split(";");
        for (int i = 0; i < sss.length; i++) {
            String[] s4 = sss[i].split(":");
            String name = s4[0];
            System.out.println(name);
            String  port= s4[1];
            System.out.println(port);
            String log="/logs/"+name+"/";
            System.out.println(log);
            String yaml = "NAOCS(Application.yml+"+name+".yml)";
            System.out.println(yaml);
            System.out.println("==============================================================================");
        }
        // int port = 3136;
        // for (int i = 0; i < 1000; i++) {
        //     System.out.println("http_port "+(port+i));
        // }
    }

    // 快速排序
    void quick_sort(int s[], int l, int r) {
        if (l < r) {
            // Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if (i < j)
                    s[i++] = s[j];

                while (i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if (i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }
}
