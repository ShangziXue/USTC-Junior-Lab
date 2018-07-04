import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class snoop {	
	/*****����panel2~panel5******/
	// static Mypanel panel2 =new Mypanel(0);
	// static Mypanel panel3 =new Mypanel(1);
	// static Mypanel panel4 =new Mypanel(2);
	// static Mypanel panel5 =new Mypanel(3);

	/*********memory�ı���*********/
	static String[] Mem_ca={
			"Memory0","Memory1","Memory2"
	};
	
	/*********memory�е�����*********/
	static String[][] Mem_Content ={
			{"0","10","20"},{"1","11","21"},{"2","12","22"},
			{"3","13","23"},{"4","14","24"},{"5","15","25"},
			{"6","16","26"},{"7","17","27"},{"8","18","28"},
			{"9","19","29"}
	};
	static Mypanel[] panel = new Mypanel[4];
	static JTable table_2 = new JTable(Mem_Content,Mem_ca); 
	static JLabel info_label = new JLabel("���/����:"); 

	static JComboBox<String> Mylistmodel1_1 = new JComboBox<>(new Mylistmodel());
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
		
		int id;
		Color Gray = new Color(192, 192, 192);
		private int[] cache_addr = {-1, -1, -1, -1};
		private Color[] color = {Gray, Gray, Gray, Gray};
		private String[] cache_op = {"", "", "", ""};
		private String[] cache_state = {"I", "I", "I", "I"};

		/*********cache�еı���*********/
		String[] Cache_ca={"Cache","��/д","Ŀ���ַ"};
		/*********cache�е�����*********/
		String[][] Cache_Content = {
				{"0"," "," "},{"1"," "," "},{"2"," "," "},{"3"," "," "}
		};
		/************cache�Ĺ���ģ��***********/
		JTable table_1 = new JTable(Cache_Content,Cache_ca); 
		JScrollPane scrollPane = new JScrollPane(table_1);
		/*
		/************memory�Ĺ���ģ��**********
		JTable table_2 = new JTable(Mem_Content,Mem_ca); 
		JScrollPane scrollPane2 = new JScrollPane(table_2);
		*/
		public Mypanel(int k){
			super();
			id = k;
			setSize(350, 250);
			setLayout(null);
			
			/*****���ԭ��********/
			add(jtext);
			add(label);
			add(label_2);
			add(button);
			add(Mylistmodel);
			add(scrollPane);
			//add(scrollPane2);
			
			/****����ԭ����С������********/
			label_2.setFont(new Font("���Ŀ���",0,16));
			label_2.setBounds(10, 10, 100, 30);
			
			label.setFont(new Font("���Ŀ���",0,16));
			label.setBounds(10, 50, 100, 30);
			
			jtext.setFont(new Font("���Ŀ���",0,15));
			jtext.setBounds(100, 50, 50, 30);

			table_1.getTableHeader().setFont(new Font("���Ŀ���",0,15));
			table_1.setFont(new Font("���Ŀ���",0,15));
			
			Mylistmodel.setSelectedItem("��");
			Mylistmodel.setFont(new Font("���Ŀ���",0,15));
			Mylistmodel.setBounds(160, 50, 50, 30);
			
			scrollPane.setFont(new Font("���Ŀ���",0,15));
			scrollPane.setBounds(10, 90, 310, 90);
			
			//scrollPane2.setFont(new Font("���Ŀ���",1,15));
			//scrollPane2.setBounds(10, 190, 310, 180);
			
			button.setFont(new Font("���Ŀ���",0,15));
			button.setBounds(220,50, 100, 35);
			
			/******��Ӱ�ť�¼�********/
			button.addActionListener(this);
			this.setColor(table_1, color);
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

			this.show_cache();
			setVisible(false);
			setVisible(true);
			
		}

		// color
		public static void setColor(JTable table,Color[] color) {
		        try {
		            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer() {
		            	@Override
		            	public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		            		setBackground(color[row]);
		            		setForeground(Color.WHITE);
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


			public void simulate_cache(){

			String addr_text = this.jtext.getText();
			if(addr_text == "")
				return;
			String info = "<html><body>���/����:<br>";
			int i, index0 = -1, index = -1, k = 1;
			int addr = Integer.parseInt(addr_text);
			int type = Mylistmodel1_1.getSelectedIndex();
			int op = this.Mylistmodel.getSelectedIndex();

			if(addr > 29){
				System.out.println("�����ַ����ӦС��30");
				info += "�����ַ����ӦС��30</body></html>";
				info_label.setText(info);
				return;
			}

			// result: hit
			for(i = 0;i < 4;i++)
				if(cache_addr[i] == addr && cache_state[i] != "I"){
					String s = "�ڴ�����" + Integer.toString(this.id + 1)
					 + "�������ڴ��ַ" + Integer.toString(addr);
					 System.out.println(s);
					 info += k++ + s + "<br>";
					index = i;
					break;
				}

			// result: miss
			if(i == 4){
				String s = "�ڴ�����" + Integer.toString(this.id + 1)
				 + "��δ�����ڴ��ַ" + Integer.toString(addr);
				 System.out.println(s);
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
				// action: replace 
				if(index == -1){
					index = index0;
					if(cache_state[index] == "S"){
						// do nothing
						s = "��Cache�е�" + Integer.toString(this.cache_addr[index]) + "�滻Ϊ" + Integer.toString(addr);
						System.out.println(s);
						info += k++ + s + "<br>";
					}
					if(cache_state[index] == "M"){
						s = "��������" +  Integer.toString(this.id + 1) + "Cache�еĵ�"
						 + Integer.toString(index + 1) + "��д�ص����洢���е�ַΪ" + Integer.toString(addr) + "������";
						 System.out.println(s);
						 info += k++ + s + "<br>";
					}
					// state: S->I or M -> I
					cache_state[index] = "I";
				}	
				cache_addr[index] = addr;		
			}

			// action: Process Read
			if(op == 0){
				if(index0 == -1){
					// state: M or S
					String s = "ֱ�Ӵ�Cahce�ж�ȡ���ݵ�CPU";
					System.out.println(s);
					info += k++ + s + "<br>";
				}
				else{
					// state: I -> S
					boolean found_ex = false;
					for(i = 0;i < 4;i++){
						if(this.id == i)
							continue;
						for(int j = 0;j < 4;j++){
							if(panel[i].cache_addr[j] == addr && panel[i].cache_state[j] == "M"){
								// Observe: BusRd
								// action: flush
								// state: M -> S
								String s1 = "�ڴ�����" + Integer.toString(i + 1) + "Cache�ж���ÿ�,ͨ������д�ش洢��";
								String s2 = "�����Ż���ֱ���������ϴ�" + Integer.toString(i + 1) + "��Cache�д��͵���ǰCache��";
								System.out.println(s1);
								System.out.println(s2);
								info += k++ + s2 + "<br>" + s1 + "<br>";
								panel[i].cache_state[j] = "S";		
								found_ex = true;
								break;				
							}

						}
						if(found_ex)
							break;
					}
					if(found_ex == false){				
						String s = "�Ӵ洢���ж�ȡ���ݵ�Cache�У�Ȼ���Cache�ж�ȡ���ݵ�CPU";
						System.out.println(s);
						info += k++ + s + "<br>";						
					}
					this.cache_state[index] = "S";
				}
				this.cache_op[index] = "��";
			}

			// action: Process Write
			if(op == 1){
				boolean bus_rdx = false;
				if(this.cache_state[index] == "M"){
					// state: M -> M
					String s = "Cache�еĶ�������±�д������";
					System.out.println(s);
					info += k++ + s + "<br>";					
				}
				if(this.cache_state[index] == "S"){
					// state: S -> M
					// signal: BusRdX
					this.cache_state[index] = "M";
					bus_rdx = true;
				}
				if(this.cache_state[index] == "I"){
					// state: I -> M
					// signal: BusRdX
					this.cache_state[index] = "M";
					bus_rdx = true;
				}
				// Observe: BusRdX
				if(bus_rdx){
					for(i = 0;i < 4;i++){
						if(this.id == i)
							continue;
						for(int j = 0;j < 4;j++){
							if(panel[i].cache_addr[j] == addr && panel[i].cache_state[j] == "M"){
								String s1 = "�ڴ�����" + Integer.toString(i + 1) + "Cache�ж���ÿ�,Ҫͨ������д�ش洢��";
								String s2 = "�����Ż���ֱ���������ϴ�" + Integer.toString(i + 1) + "��Cache�д��͵���ǰCache��";
								System.out.println(s1);
								System.out.println(s2);
								info += k++ + s1 + "<br>" + s2 + "<br>";
								panel[i].cache_state[j] = "I";
								break;										
							}
							if(panel[i].cache_addr[j] == addr && panel[i].cache_state[j] == "S"){
								String s = "�ڴ�����" + Integer.toString(i + 1) + "Cache�й���ÿ�,д����";
								System.out.println(s);
								info += k++ + s + "<br>";
								panel[i].cache_state[j] = "I";
							}	
						}		
					}
					String s = "Cache�ڵ��¿鱻д�����ݣ���Ϊ�����";
					System.out.println(s);
					info += k++ + s + "<br>";					
				}

				this.cache_op[index] = "д";
			}

			info += "</body></html>";
			info_label.setText(info);
			for(i = 0;i < 3;i++){
				if(i == addr / 10)
					memsetColor(table_2, addr % 10, addr / 10, new Color(205, 85, 85));
				else
					memsetColor(table_2, 0, i, new Color(255,222,173));
			}
			table_2.updateUI();

		}

		public void show_cache(){
			for(int i = 0;i < 4;i++){
				if(this.cache_state[i] == "I"){
					this.Cache_Content[i][1] = "";
					this.Cache_Content[i][2] = "";
					color[i] = Gray;
				}
				else{
					this.Cache_Content[i][1] = this.cache_op[i];
					this.Cache_Content[i][2] =  Integer.toString(this.cache_addr[i]);
					// System.out.println(this.cache_state[i]);
					if(this.cache_state[i] == "M")
						color[i] = Color.PINK;
					else
						color[i] = new Color(0, 197, 205);
				}
				this.table_1.updateUI();
				// this.table_1.setValueAt(this.Cache_Content[i][1], i, 1);
				// this.table_1.setValueAt(this.Cache_Content[i][2], i, 2);
			}
			this.setColor(table_1, color);

		}

		public void actionPerformed(ActionEvent e){
			/******��д�Լ��Ĵ�����*******/

			this.simulate_cache();

			panel[0].show_cache();
			panel[1].show_cache();
			panel[2].show_cache();
			panel[3].show_cache();
			
			/**********��ʾˢ�º������********/
			panel[0].setVisible(false);
			panel[0].setVisible(true);
			panel[1].setVisible(false);
			panel[1].setVisible(true);					
			panel[2].setVisible(false);
			panel[2].setVisible(true);
			panel[3].setVisible(false);
			panel[3].setVisible(true);
		}
	}

    public static void memsetColor(JTable table, int rowIndex, int columnIndex, Color color) {  
        try {  
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {  
  
                public Component getTableCellRendererComponent(JTable table,  
                        Object value, boolean isSelected, boolean hasFocus,  
                        int row, int column) {  
                    if (row == rowIndex) {  
                        setBackground(color);  
                        setForeground(Color.BLACK);  
                    }else if(row > rowIndex){  
                        setBackground(new Color(255,222,173));  
                        setForeground(Color.BLACK);  
                    }else{  
                        setBackground(new Color(255,222,173));  
                        setForeground(Color.BLACK);  
                    }  
  
                    return super.getTableCellRendererComponent(table, value,  
                            isSelected, hasFocus, row, column);  
                }  
            };  

            table.getColumn(table.getColumnName(columnIndex)).setCellRenderer(tcr);  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame myjf = new JFrame("��cacheһ����ģ��֮������");
		myjf.setSize(1500, 600);
		myjf.setLayout(null);
		myjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container C1 = myjf.getContentPane();
		
		JScrollPane scrollPane2 = new JScrollPane(table_2);
		table_2.getTableHeader().setFont(new Font("���Ŀ���",0,15));
		table_2.setFont(new Font("���Ŀ���",0,15));	
		memsetColor(table_2, 0, 0, new Color(255,222,173));
		memsetColor(table_2, 0, 1, new Color(255,222,173));
		memsetColor(table_2, 0, 2, new Color(255,222,173));
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
		C1.add(scrollPane2);
		panel[0].setBounds(10, 100, 350, 200);
		panel[1].setBounds(360, 100, 350, 200);
		panel[2].setBounds(720, 100, 350, 200);
		panel[3].setBounds(1080, 100, 350, 200);
		info_label.setBounds(20,300,380,180);
		scrollPane2.setBounds(400,350,1000,180);
		info_label.setFont(new Font("���Ŀ���",0,15));
		scrollPane2.setFont(new Font("���Ŀ���",0,15));
		//scrollPane2.setBounds(100, 250, 310, 180);
		
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
		
		
		//panel2.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory1");
		//panel3.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory2");
		//panel4.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory3");
		//panel5.table_2.getColumnModel().getColumn(0).setHeaderValue("Memory4");
		
		for(int i=0;i<10;i++){
			//panel3.Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel3.Mem_Content[i][0])+10));
			//panel4.Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel3.Mem_Content[i][0])+20));
			//panel5.Mem_Content[i][0]=String.valueOf((Integer.parseInt(panel3.Mem_Content[i][0])+30));
		}
		/********����ͷ��panel*****/
		panel1.setBounds(10, 10, 1500, 100);
		panel1.setLayout(null);
		
		JLabel label1_1=new JLabel("ִ�з�ʽ:����ִ��");
		label1_1.setFont(new Font("���Ŀ���",0,20));
		label1_1.setBounds(15, 15, 200, 40);
		panel1.add(label1_1);
		
		//JComboBox<String> Mylistmodel1_1 = new JComboBox<>(new Mylistmodel());
		Mylistmodel1_1.setBounds(220, 15, 150, 40);
		Mylistmodel1_1.setFont(new Font("���Ŀ���",0,20));
		panel1.add(Mylistmodel1_1);
		
		JButton button1_1=new JButton("��λ");
		button1_1.setFont(new Font("���Ŀ���",0,18));
		button1_1.setBounds(400, 15, 90, 40);
		
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

