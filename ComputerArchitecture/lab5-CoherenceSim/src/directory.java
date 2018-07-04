import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class directory {	
	/*****����panel2~panel5******/
	// static Mypanel panel2 =new Mypanel();
	// static Mypanel panel3 =new Mypanel();
	// static Mypanel panel4 =new Mypanel();
	// static Mypanel panel5 =new Mypanel();
	static Mypanel[] panel = new Mypanel[4];
	static JLabel info_label = new JLabel("���/����:"); 

	static JComboBox<String> Mylistmodel1_1 = new JComboBox<>(new Mylistmodel());
	static JComboBox<String> Mylistmodel1 = new JComboBox<>(new Mylistmodel1());
	static class Mylistmodel extends AbstractListModel<String> implements ComboBoxModel<String>{		
		private static final long serialVersionUID = 1L;
		String selecteditem="ֱ��ӳ��";
		private String[] test={"ֱ��ӳ��","��·������","��·������"};
		public String getElementAt(int index){
			return test[index];
		}
		public int getSize(){
			return test.length;
		}
		public void setSelectedItem(Object item){
			selecteditem=(String)item;
		}
		public Object getSelectedItem( ){
			return selecteditem;
		}
		public int getIndex() {
			for (int i = 0; i < test.length; i++) {
				if (test[i].equals(getSelectedItem()))
					return i;
			}
			return 0;
		}
		
	}
	static class Mylistmodel1 extends AbstractListModel<String> implements ComboBoxModel<String>{		
		private static final long serialVersionUID = 1L;
		String selecteditem="����ִ��";
		private String[] test={"����ִ��", "����ִ��"};
		public String getElementAt(int index){
			return test[index];
		}
		public int getSize(){
			return test.length;
		}
		public void setSelectedItem(Object item){
			selecteditem=(String)item;
		}
		public Object getSelectedItem( ){
			return selecteditem;
		}
		public int getIndex() {
			for (int i = 0; i < test.length; i++) {
				if (test[i].equals(getSelectedItem()))
					return i;
			}
			return 0;
		}
		
	}
	static class Mylistmodel2 extends AbstractListModel<String> implements ComboBoxModel<String>{		
		private static final long serialVersionUID = 1L;
		String selecteditem=null;
		private String[] test={"��","д"};
		public String getElementAt(int index){
			return test[index];
		}
		public int getSize(){
			return test.length;
		}
		public void setSelectedItem(Object item){
			selecteditem=(String)item;
		}
		public Object getSelectedItem( ){
			return selecteditem;
		}
		public int getIndex() {
			for (int i = 0; i < test.length; i++) {
				if (test[i].equals(getSelectedItem()))
					return i;
			}
			return 0;
		}
		
	}
	
	static class Mypanel extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		JLabel label=new JLabel("���ʵ�ַ");
		JLabel label_2=new JLabel("Process1");
		
		JTextField jtext=new JTextField("");
		JButton button=new JButton("ִ��");
		JComboBox<String> Mylistmodel = new JComboBox<>(new Mylistmodel2());
		
		int id, step;
		Color Gray = new Color(192, 192, 192);
		Color Yellow = new Color(255, 222, 173);
		private int[] cache_addr = {-1, -1, -1, -1};
		private Color[] cache_color = {Gray, Gray, Gray, Gray};
		private String[] cache_op = {"", "", "", ""};
		private String[] cache_state = {"I", "I", "I", "I"};

		private Color[] home_color = {Yellow, Yellow, Yellow, Yellow, Yellow, Yellow, Yellow, Yellow, Yellow, Yellow};
		private String[] home_state = {"I", "I", "I", "I", "I", "I", "I", "I", "I", "I"};
		private int[][] home_bits = new int[10][4];

		private int[][] cache_addr_r = new int[4][4];
		private String[][] cache_op_r = new String[4][4];
		private String[][] cache_state_r = new String[4][4];
		private String[][] home_state_r = new String[4][10];
		private int[][][] home_bits_r = new int[4][10][4];

		/*********cache�еı���*********/
		String[] Cache_ca={"Cache","��/д","Ŀ���ַ"};
		/*********cache�е�����*********/
		String[][] Cache_Content = {
				{"0"," "," "},{"1"," "," "},{"2"," "," "},{"3"," "," "}
		};
		/*********memory�ı���*********/
		String[] Mem_ca={
				"Memory","Bits",""
		};
		
		/*********memory�е�����*********/
		String[][] Mem_Content ={
				{"0","",""},{"1","",""},{"2","",""},{"3","",""},{"4","",""},{"5","",""},{"6","",""},{"7","",""},
				{"8","",""},{"9","",""}
		};
		/************cache�Ĺ���ģ��***********/
		JTable table_1 = new JTable(Cache_Content,Cache_ca); 
		JScrollPane scrollPane = new JScrollPane(table_1);
		/************memory�Ĺ���ģ��***********/
		JTable table_2 = new JTable(Mem_Content,Mem_ca); 
		JScrollPane scrollPane2 = new JScrollPane(table_2);
		
		public Mypanel(int k){
			super();
			step = 0;
			id = k;
			setSize(350, 400);
			setLayout(null);
			for(int i = 0;i < 10;i++)
				for(int j = 0;j < 4;j++)
					home_bits[i][j] = 0;
			
			/*****���ԭ��********/
			add(jtext);
			add(label);
			add(label_2);
			add(button);
			add(Mylistmodel);
			add(scrollPane);
			add(scrollPane2);
			
			/****����ԭ����С������********/
			label_2.setFont(new Font("���Ŀ���",1,16));
			label_2.setBounds(10, 10, 100, 30);
			
			label.setFont(new Font("���Ŀ���",1,16));
			label.setBounds(10, 50, 100, 30);
			
			jtext.setFont(new Font("���Ŀ���",1,15));
			jtext.setBounds(100, 50, 50, 30);
			
			Mylistmodel.setSelectedItem("��");
			Mylistmodel.setFont(new Font("���Ŀ���",1,15));
			Mylistmodel.setBounds(160, 50, 50, 30);
			
			scrollPane.setFont(new Font("���Ŀ���",1,15));
			scrollPane.setBounds(10, 90, 310, 90);
			
			scrollPane2.setFont(new Font("���Ŀ���",1,15));
			scrollPane2.setBounds(10, 190, 310, 180);
			
			button.setFont(new Font("���Ŀ���",1,15));
			button.setBounds(220,50, 100, 35);
			
			this.setColor(table_1, cache_color);
			this.setColor(table_2, home_color);

			/******��Ӱ�ť�¼�********/
			button.addActionListener(this);

		}
		
		public void init(){
			/******Mypanel�ĳ�ʼ��******/
			jtext.setText("");
			Mylistmodel.setSelectedItem("��");
			for(int i=0;i<=3;i++)
				for(int j=1;j<=2;j++)
					Cache_Content[i][j]=" ";
			for(int i=0;i<=9;i++)
				for(int j=1;j<=2;j++)
					Mem_Content[i][j]=" ";

			for(int i = 0;i < 4;i++){
				cache_addr[i] = -1;
				cache_op[i] = "";
				cache_state[i] = "I";
			}
			for(int i = 0;i < 10;i++){
				home_state[i] = "I";
				for(int j = 0;j < 4;j++)
					home_bits[i][j] = 0;
			}
			if(Mylistmodel1.getSelectedIndex() == 0)
				step = 0;
			else
				step = 100;
			this.show_cache();
			this.setColor(table_1, cache_color);
			this.setColor(table_2, home_color);
			setVisible(false);
			setVisible(true);
			
		}

		String simulate_cache(){

			String addr_text = this.jtext.getText();
			if(addr_text == ""){
				step = -1;
				return "";
			}
			String info = "<html><body>���/����:<br>";
			int i, index0 = -1, index = -1, k = 1;
			int addr = Integer.parseInt(addr_text);
			int type = Mylistmodel1_1.getSelectedIndex();
			int op = this.Mylistmodel.getSelectedIndex();

			if(addr > 39){
				info += "�����ַ����ӦС��40";
				step = -1;
				return info;
			}

			// result: hit
			for(i = 0;i < 4;i++)
				if(cache_addr[i] == addr && cache_state[i] != "I"){
					String s;
					if(op == 0)
						s = ".������";
					else
						s = ".д����";
					info += k++ + s + "<br>";
					index = i;
					if(step == 0){
						step ++;
						return info;
					}
					break;
				}

			// result: miss
			if(i == 4){
				String s;
				if(op == 0)
					s = ".��������";
				else
					s = ".д������";
				info += k++ + s + "<br>";
				if(type == 0){
					index0 = addr % 4;
					if(cache_state[index0] == "I")
						index = index0;
				}
				if(type == 1){
					index0 = 2 * (addr % 2);
					if(cache_state[index0] == "I")
						index = index0;
					else if(cache_state[index0 + 1] == "I")
						index = index0 + 1;
				}
				if(type == 2){
					index0 = 0;
					for(i = 0;i < 4;i++)
						if(cache_state[i] == "I"){
							index = i;
							break;
						}
				}

				if(step == 0)
					return info;

				// action: replace 
				if(index == -1){
					index = index0;
					if(cache_state[index] == "S"){
						s = ".���أ����滻���������㷢���޸Ĺ���(" + Integer.toString(id + 1) + ", " + Integer.toString(cache_addr[index]) + ")��Ϣ";
						info += k++ + s + "<br>";
					}
					if(cache_state[index] == "M"){
						s = ".���أ����滻���������㷢��д�ز��޸Ĺ���(" + Integer.toString(id + 1) + ", " + Integer.toString(cache_addr[index]) + ")��Ϣ";
						info += k++ + s + "<br>";
					}
					// state: S->I or M -> I
					panel[cache_addr[index] / 10].home_bits[cache_addr[index] % 10][id] = 0;
					// panel[cache_addr[index] / 10].home_state[cache_addr[index] % 10] = "I";
					cache_state[index] = "I";
					if(step == 1)
						return info;
				}
				else if(step == 1)
					step ++;
				// cache_addr[index] = addr;	
			}

			// action: Process Read
			if(op == 0){
				if(index0 == -1){
					// Hit, state: M or S
					String s = ".(���أ�ֱ�Ӵ�Cahce�ж����ݵ�CPU)";
					info += k++ + s + "<br>";
					this.cache_op[index] = "��";
					step = -1;
					return info;
				}
				// Read Miss
				else{
					String s = ".���أ����������Ͷ�������(" + Integer.toString(id + 1) + ", " + Integer.toString(addr) + ")��Ϣ";
					info += k++ + s + "<br>";
					if(step == 2)
						return info;
					// state: I -> S
					boolean found_ex = false;
					int m = -1;
					// Block: Modified
					for(i = 0;i < 4;i++){
						if(this.id == i)
							continue;
						for(int j = 0;j < 4;j++){
							if(panel[i].cache_addr[j] == addr && panel[i].cache_state[j] == "M"){
								// Observe: BusRd
								// action: flush
								// state: M -> S
								s = ".��������Զ�̽��" + Integer.toString(i + 1) + "��ȡ���ݿ�(" + addr + ")����Ϣ";
								info += k++ + s + "<br>";
								found_ex = true;
								m = j;
								if(step == 3)
									return info;
								break;				
							}

						}
						if(found_ex)
							break;
					}
					if(i == 4 && step == 3)
						step ++;
					if(found_ex){
						String s2 = ".Զ�̣������ݿ��͸��������";
						info += k++ + s + "<br>";
						if(step == 4)
							return info;
					}
					else if(step == 4)
						step ++;
					// Block: Shared or Owned, (also Modified do this), (uncached may be the same...)
					cache_state[index] = "S";
					if(found_ex)
						panel[i].cache_state[m] = "S";
					cache_addr[index] = addr;
					this.cache_op[index] = "��";
					s = ".�����������ݿ��͸����ؽ��";
					info += k++ + s + "<br>";
					if(step == 5)
						return info;

					String shared_set = "";
					for(int l = 0;l < 4;l++)
						if(panel[addr / 10].home_bits[addr % 10][l] == 1)
							shared_set += Integer.toString(l + 1);
					panel[addr / 10].home_state[addr % 10] = "S";
					panel[addr / 10].home_bits[addr % 10][id] = 1;

					if(shared_set == "")
						s = ".������Ϊ��{" + Integer.toString(id + 1) + "}";
					else
						s = ".������Ϊ��{" + shared_set + "} + {" + Integer.toString(id + 1) + "}";
					info += k++ + s + "<br>";
					step = -1;
					return info;
				}
			}
			// action: Process Write
			if(op == 1){
				boolean bus_rdx = false;
				// hit, local state is M
				if(this.cache_state[index] == "M"){
					// state: M -> M
					// cache_addr[index] = addr;
					String s = ".(���أ�ֱ�Ӵ�CPU��д���ݵ�Cache)";
					info += k++ + s + "<br>";	
					this.cache_op[index] = "д";
					step = -1;
					return info;				
				}
				// hit, local state is S
				if(this.cache_state[index] == "S"){
					// state: S -> M
					// signal: BusRdX
					String s = s = ".���أ�����������д����(" + Integer.toString(id + 1) + ", " + Integer.toString(addr) + ")��Ϣ";
					info += k++ + s + "<br>";					
					// this.cache_state[index] = "M";
					// cache_addr[index] = addr;
					bus_rdx = true;
					if(step == 2)
						return info;					
				}
				// Miss
				if(this.cache_state[index] == "I"){
					// state: I -> M
					// signal: BusRdX
					String s = ".���أ�����������д������(" + Integer.toString(id + 1) + ", " + Integer.toString(addr) + ")��Ϣ";
					info += k++ + s + "<br>";
					bus_rdx = true;
					if(step == 2)
						return info;				
				}	

				// Observe: BusRdX
				if(bus_rdx){
					String home = panel[addr / 10].home_state[addr % 10];
					// Home: Shared
					if(home == "S"){
						String s = ".";
						for(i = 0;i < 4;i++){
							if(this.id == i)
								continue;
							for(int j = 0;j < 4;j++){
								if(panel[i].cache_addr[j] == addr && panel[i].cache_state[j] == "S"){
									 s += "��������Զ�̽�㷢��ȡ�����ϣ�"+ Integer.toString(addr) +"������Ϣ<br>";					
									panel[i].cache_state[j] = "I";
									panel[addr / 10].home_bits[addr % 10][i] = 0;
								}
							}		
						}
						if(step == 3 && s != ".")
						{
							step ++;
							info += k++ + s;
							return info;
						}
					}
					// Home: Modified
					else if(home == "M"){
						int p = -1, c = -1;;
						for(i = 0;i < 4;i++){
							if(this.id == i)
								continue;
							for(int j = 0;j < 4;j++){
								if(panel[i].cache_addr[j] == addr && panel[i].cache_state[j] == "M"){
									String s1 = "��������Զ�̽�㷢��ȡ�����ϣ�"+ Integer.toString(addr) +"������Ϣ";
									info += k++ + s1 + "<br>";
									p = i;
									c = j;
									if(step == 3)
										return info;
									break;								
								}
							}
							if(c > -1)
								break;		
						} 

						String s  = ".Զ�̣������ݿ��͸��������<br>    ��cache�еĸÿ�����";
						info += k++ + s + "<br>";
						panel[addr / 10].home_bits[addr % 10][p] = 0;
						panel[p].cache_state[c] = "I";
						if(step == 4)
							return info;
					}
					else if(step == 3)
						step += 2;

					this.cache_state[index] = "M";
					cache_addr[index] = addr;
					this.cache_op[index] = "д";
					String s = ".�����������ݿ��͸����ؽ��";
					info += k++ + s + "<br>";
					if(step == 5)
						return info;
					step = -1;

					String shared_set = "";
					for(int l = 0;l < 4;l++)
						if(panel[addr / 10].home_bits[addr % 10][l] == 1)
							shared_set += Integer.toString(l + 1);
					panel[addr / 10].home_state[addr % 10] = "M";
					panel[addr / 10].home_bits[addr % 10][id] = 1;
					if(shared_set == "" || shared_set ==  Integer.toString(id))
						s = ".������Ϊ��{" + Integer.toString(id + 1) + "}";
					else
						s = ".������Ϊ��{" + shared_set + "} + {" + Integer.toString(id + 1) + "}";
					info += k++ + s + "<br>";
					step = -1;
					return info;

				}
			}
			return "";
		}

		void back_up(){
			for(int k = 0;k < 4;k ++){
				for(int i = 0;i < 4;i++){
					cache_addr_r[k][i] = panel[k].cache_addr[i];
					cache_op_r[k][i] = panel[k].cache_op[i];
					cache_state_r[k][i] = panel[k].cache_state[i];
				}
				for(int i = 0;i < 10;i++){
					home_state_r[k][i] = panel[k].home_state[i];
					for(int j = 0;j < 4;j++)
						home_bits_r[k][i][j] = panel[k].home_bits[i][j];
				}
			}	
		}

		void recover(){
			for(int k = 0;k < 4;k ++){
				for(int i = 0;i < 4;i++){
					panel[k].cache_addr[i] = cache_addr_r[k][i];
					panel[k].cache_op[i] = cache_op_r[k][i];
					panel[k].cache_state[i] = cache_state_r[k][i];
				}
				for(int i = 0;i < 10;i++){
					panel[k].home_state[i] = home_state_r[k][i];
					for(int j = 0;j < 4;j++)
						panel[k].home_bits[i][j] = home_bits_r[k][i][j];
				}
			}	
		}


		void show_cache(){
			for(int i = 0;i < 4;i++){
				if(this.cache_state[i] == "I"){
					this.Cache_Content[i][1] = "";
					this.Cache_Content[i][2] = "";
					cache_color[i] = Gray;
				}
				else{
					this.Cache_Content[i][1] = this.cache_op[i];
					this.Cache_Content[i][2] =  Integer.toString(this.cache_addr[i]);
					// System.out.println(this.cache_state[i]);
					if(this.cache_state[i] == "M")
						cache_color[i] = Color.PINK;
					else
						cache_color[i] = new Color(0, 197, 205);
				}			
				this.table_1.updateUI();
			}
			for(int i = 0;i < 10;i++){
				if(this.home_state[i] == "I"){
					this.Mem_Content[i][1] = "";
					home_color[i] = Yellow;
				}
				else{
					String shared_set = "";
					for(int j = 0;j < 4;j++)
						if(home_bits[i][j] == 1)
							shared_set += Integer.toString(j + 1);
					this.Mem_Content[i][1] = shared_set;
					// System.out.println(this.cache_state[i]);
					if(this.home_state[i] == "M")
						home_color[i] = Color.PINK;
					else
						home_color[i] = new Color(0, 197, 205);
				}
				this.table_2.updateUI();	
			}
			this.setColor(table_1, cache_color);
			this.setColor(table_2, home_color);
		}		


		// color
		public static void setColor(JTable table,Color[] color) {
		        try {
		            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer() {
		            	@Override
		            	public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		            		setBackground(color[row]);
		            		setForeground(Color.BLACK);
		            		return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
		            	}
		            };
		            // ��ÿ�е�ÿһ����Ԫ��
		            int columnCount = table.getColumnCount();
		            for (int i = 0; i < columnCount; i++) {
		                table.getColumn(table.getColumnName(i)).setCellRenderer(dtcr);
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }	


		public void actionPerformed(ActionEvent e){
			/******��д�Լ��Ĵ�����*******/
			back_up();
			String info = simulate_cache();

			if(info != ""){
				info += "</body></html>";
				info_label.setText(info);
				step ++;
			};
			System.out.println(step);

			panel[0].show_cache();
			panel[1].show_cache();
			panel[2].show_cache();
			panel[3].show_cache();	
			if(step > 0 && step < 100)
				recover();		
			
			
			/**********��ʾˢ�º������********/
			
			if(step > 0)
				for(int i = 0;i < 4;i++){
					if(i != id)
						panel[i].button.setEnabled(false);
				}
			else
				for(int i = 0;i < 4;i++)
					panel[i].button.setEnabled(true);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame myjf = new JFrame("��cacheһ����ģ��֮Ŀ¼��");
		myjf.setSize(1500, 600);
		myjf.setLayout(null);
		myjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container C1 = myjf.getContentPane();
		
		/*****�½�panel1*****/
		JPanel panel1 = new JPanel();

		panel[0] = new Mypanel(0);
		panel[1] = new Mypanel(1);
		panel[2] = new Mypanel(2);
		panel[3] = new Mypanel(3);
		C1.add(panel[0]);
		C1.add(panel[1]);
		C1.add(panel[2]);
		C1.add(panel[3]);
		C1.add(info_label);

		info_label.setBounds(500,0,500,100);
		info_label.setFont(new Font("���Ŀ���",0,15));

		panel[0].setBounds(10, 100, 350, 400);
		panel[1].setBounds(360, 100, 350, 400);
		panel[2].setBounds(720, 100, 350, 400);
		panel[3].setBounds(1080, 100, 350, 400);
		
		
		/********����ÿ��Mypanel�Ĳ�ͬ�Ĳ���************/
		panel[0].label_2.setText("Process1");
		panel[1].label_2.setText("Process2");
		panel[2].label_2.setText("Process3");
		panel[3].label_2.setText("Process4");
		panel[0].table_1.getColumnModel().getColumn(0).setHeaderValue("cache1");
		panel[0].Cache_ca[0]="cache1";
		panel[1].table_1.getColumnModel().getColumn(0).setHeaderValue("cache2");
		panel[1].Cache_ca[0]="cache2";
		panel[2].table_1.getColumnModel().getColumn(0).setHeaderValue("cache3");
		panel[2].Cache_ca[0]="cache3";
		panel[3].table_1.getColumnModel().getColumn(0).setHeaderValue("cache4");
		panel[3].Cache_ca[0]="cache4";
		
		panel[0].table_2.getColumnModel().getColumn(0).setHeaderValue("memory1");
		panel[0].Mem_ca[0]="memory1";
		panel[1].table_2.getColumnModel().getColumn(0).setHeaderValue("memory2");
		panel[1].Mem_ca[0]="memory2";
		panel[2].table_2.getColumnModel().getColumn(0).setHeaderValue("memory3");
		panel[2].Mem_ca[0]="memory3";
		panel[3].table_2.getColumnModel().getColumn(0).setHeaderValue("memory4");
		panel[3].Mem_ca[0]="memory4";
		
		for(int i=0;i<10;i++){
			panel[1].Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel[1].Mem_Content[i][0])+10));
			panel[2].Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel[2].Mem_Content[i][0])+20));
			panel[3].Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel[3].Mem_Content[i][0])+30));
		}
		/********����ͷ��panel*****/
		panel1.setBounds(10, 10, 1500, 100);
		panel1.setLayout(null);
		
		JLabel label1_1=new JLabel("ִ�з�ʽ:");
		label1_1.setFont(new Font("���Ŀ���",1,20));
		label1_1.setBounds(15, 15, 100, 40);
		Mylistmodel1.setFont(new Font("���Ŀ���",1,20));
		Mylistmodel1.setBounds(115, 15, 120, 40);
		panel1.add(Mylistmodel1);
		panel1.add(label1_1);
		
		//JComboBox<String> Mylistmodel1_1 = new JComboBox<>(new Mylistmodel());
		Mylistmodel1_1.setBounds(240, 15, 150, 40);
		Mylistmodel1_1.setFont(new Font("���Ŀ���",1,20));
		panel1.add(Mylistmodel1_1);
		
		JButton button1_1=new JButton("��λ");
		button1_1.setBounds(400, 15, 70, 40);
		
		/**********��λ��ť�¼�����ʼ����***********/
		button1_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				panel[0].init();
				panel[1].init();
				panel[2].init();
				panel[3].init();
				Mylistmodel1_1.setSelectedItem("ֱ��ӳ��");
				
			}
		});
		
		/*panel2.Mem_Content[1][1]="11";*/
		panel1.add(button1_1);
		C1.add(panel1);
		myjf.setVisible(true);
		

		
	}

	
}

