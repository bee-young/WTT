package db.product;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ProductInternalFrame extends JInternalFrame {
	DefaultTableModel model;
	DefaultTableCellRenderer rRender, cRender;
	ProductDao dao;
	
	
	private JLabel lblNewLabel;
	private JTextField tfSno;
	private JTextField tfCode;
	private JTextField tfCodeName;
	private JTextField tfNal;
	private JTextField tfEa;
	private JTextField tfPrice;
	private JSeparator separator;
	private JTextField tfAmt;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JButton btnSave;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnCancel;
	private JButton btnSearch;
	private JTextField tfFindStr;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnFind;
	private JTextField tfSpec;
	private JLabel lblNewLabel_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInternalFrame frame = new ProductInternalFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductInternalFrame() {
		super("생산관리", true, true, true, true);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		setBounds(100, 100, 965, 588);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getTfSno());
		getContentPane().add(getTfCode());
		getContentPane().add(getTfCodeName());
		getContentPane().add(getTfNal());
		getContentPane().add(getTfEa());
		getContentPane().add(getTfPrice());
		getContentPane().add(getSeparator());
		getContentPane().add(getTfAmt());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getLblNewLabel_2());
		getContentPane().add(getLblNewLabel_3());
		getContentPane().add(getLblNewLabel_4());
		getContentPane().add(getLblNewLabel_5());
		getContentPane().add(getLblNewLabel_6());
		getContentPane().add(getLblNewLabel_7());
		getContentPane().add(getBtnSave());
		getContentPane().add(getBtnModify());
		getContentPane().add(getBtnDelete());
		getContentPane().add(getBtnCancel());
		getContentPane().add(getBtnSearch());
		getContentPane().add(getTfFindStr());
		getContentPane().add(getScrollPane());
		setVisible(true);

		dao = new ProductDao();

		rRender = new DefaultTableCellRenderer();
		cRender = new DefaultTableCellRenderer();
		
		rRender.setHorizontalAlignment(JLabel.RIGHT);
		cRender.setHorizontalAlignment(JLabel.CENTER);
		
		String[] header = {"순번", "제품코드", "제품명", "판매일자", "수량", "단가", "금액"};
		model = new DefaultTableModel(header, 0);
		table.setModel(model);
		
		// 셀 넓이 설정
		table.getColumn("순번").setPreferredWidth(14);
		table.getColumn("제품코드").setPreferredWidth(24);

		// 셀 가운데 정렬
		table.getColumn("제품코드").setCellRenderer(cRender);
		table.getColumn("판매일자").setCellRenderer(cRender);
		
		// 셀 오른쪽 정렬
		table.getColumn("단가").setCellRenderer(rRender);
		table.getColumn("수량").setCellRenderer(rRender);
		table.getColumn("금액").setCellRenderer(rRender);
		
		getContentPane().add(getBtnFind());
		getContentPane().add(getTfSpec());
		getContentPane().add(getLblNewLabel_8());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		tfNal.setText(sdf.format(new Date()));
		
	}
	
	public ProductVo getVo() {
		ProductVo vo = null;
		try {
			
			int sno=0;
			if( !tfSno.getText().equals("") ) {
				sno = Integer.parseInt(tfSno.getText());
			}
			String code = tfCode.getText();
			String codeName = tfCodeName.getText();
			String nal = tfNal.getText();
			int ea = Integer.parseInt(tfEa.getText());
			int price = Integer.parseInt(tfPrice.getText());

			vo = new ProductVo(sno, code, codeName, nal, ea, price);
			
		}catch(Exception ex) {
			ProductFrame.info.setText("입력 자료를 확인해 주세요");
		}
		
		return vo;			

	}

	public void setVo(ProductVo vo) {
		tfSno.setText(vo.getSno()+"");
		tfCode.setText(vo.getCode());
		tfCodeName.setText(vo.getCodeName());
		tfSpec.setText(vo.getSpec());
		tfNal.setText(vo.getNal());
		tfEa.setText(vo.getEa()+"");
		tfPrice.setText(vo.getPrice()+"");
		tfAmt.setText(vo.getAmt()+"");
	}
	
	public void computeAmt() {
		String tempEa = tfEa.getText();
		String tempPrice = tfPrice.getText();
		
		try {
			int ea = Integer.parseInt(tempEa);
			int price = Integer.parseInt(tempPrice);
			int amt = ea*price;
			DecimalFormat df = new DecimalFormat("###,###");
			tfAmt.setText(df.format(amt));
		}catch(Exception ex) {
			tfAmt.setText("0");
			ProductFrame.info.setText("수량또는 금액을 확인바람.");
		}
		
		
	}
	
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("판매관리");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("양재인장체M", Font.BOLD, 20));
			lblNewLabel.setForeground(Color.RED);
			lblNewLabel.setBounds(271, 10, 271, 50);
		}
		return lblNewLabel;
	}
	public JTextField getTfSno() {
		if (tfSno == null) {
			tfSno = new JTextField();
			tfSno.setEditable(false);
			tfSno.setBounds(104, 89, 77, 21);
			tfSno.setColumns(10);
		}
		return tfSno;
	}
	public JTextField getTfCode() {
		if (tfCode == null) {
			tfCode = new JTextField();
			tfCode.setBounds(104, 120, 88, 21);
			tfCode.setColumns(10);
		}
		return tfCode;
	}
	public JTextField getTfCodeName() {
		if (tfCodeName == null) {
			tfCodeName = new JTextField();
			tfCodeName.setEditable(false);
			tfCodeName.setBounds(104, 151, 155, 21);
			tfCodeName.setColumns(10);
		}
		return tfCodeName;
	}
	public JTextField getTfNal() {
		if (tfNal == null) {
			tfNal = new JTextField();
			tfNal.setBounds(104, 213, 77, 21);
			tfNal.setColumns(10);
		}
		return tfNal;
	}
	public JTextField getTfEa() {
		if (tfEa == null) {
			tfEa = new JTextField();
			tfEa.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					computeAmt();
				}
			});
			tfEa.setBounds(104, 244, 77, 21);
			tfEa.setColumns(10);
		}
		return tfEa;
	}
	public JTextField getTfPrice() {
		if (tfPrice == null) {
			tfPrice = new JTextField();
			tfPrice.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					computeAmt();
				}
			});
			tfPrice.setBounds(104, 275, 88, 21);
			tfPrice.setColumns(10);
		}
		return tfPrice;
	}
	public JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBounds(27, 314, 213, 2);
		}
		return separator;
	}
	public JTextField getTfAmt() {
		if (tfAmt == null) {
			tfAmt = new JTextField();
			tfAmt.setEditable(false);
			tfAmt.setBounds(104, 326, 116, 21);
			tfAmt.setColumns(10);
		}
		return tfAmt;
	}
	public JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("순번");
			lblNewLabel_1.setBounds(27, 92, 57, 15);
		}
		return lblNewLabel_1;
	}
	public JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("제품코드");
			lblNewLabel_2.setBounds(27, 123, 57, 15);
		}
		return lblNewLabel_2;
	}
	public JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("제품명");
			lblNewLabel_3.setBounds(27, 154, 57, 15);
		}
		return lblNewLabel_3;
	}
	public JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("판매일자");
			lblNewLabel_4.setBounds(27, 216, 57, 15);
		}
		return lblNewLabel_4;
	}
	public JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("수량");
			lblNewLabel_5.setBounds(27, 247, 57, 15);
		}
		return lblNewLabel_5;
	}
	public JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("단가");
			lblNewLabel_6.setBounds(27, 278, 57, 15);
		}
		return lblNewLabel_6;
	}
	public JLabel getLblNewLabel_7() {
		if (lblNewLabel_7 == null) {
			lblNewLabel_7 = new JLabel("금액");
			lblNewLabel_7.setBounds(27, 329, 57, 15);
		}
		return lblNewLabel_7;
	}
	public JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("저장");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ProductVo vo = getVo();
					if(vo != null) {
						String msg = dao.insert(vo);
						ProductFrame.info.setText(msg);
					}
					
					
				}
			});
			btnSave.setBounds(12, 365, 68, 23);
		}
		return btnSave;
	}
	public JButton getBtnModify() {
		if (btnModify == null) {
			btnModify = new JButton("수정");
			btnModify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ProductVo vo = getVo();
					if( vo != null) {
						String msg = dao.update(vo);
						ProductFrame.info.setText(msg);
						btnSearch.doClick();
					}

					
				}
			});
			btnModify.setBounds(79, 365, 68, 23);
		}
		return btnModify;
	}
	public JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int sno = Integer.parseInt(tfSno.getText() );
					String msg = dao.delete(sno);
					ProductFrame.info.setText(msg);
					
					tfSno.setText("");
					tfCode.setText("");
					tfCodeName.setText("");
					tfSpec.setText("");
					tfEa.setText("");
					tfPrice.setText("");
					tfAmt.setText("");
				
					btnSearch.doClick();
					tfCode.requestFocus();					
				
					
				}
			});
			btnDelete.setBounds(147, 365, 68, 23);
		}
		return btnDelete;
	}
	public JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("취소");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnSave.setEnabled(true);
					btnModify.setEnabled(false);
					btnDelete.setEnabled(false);
					btnFind.setEnabled(true);
					
					tfSno.setText("");
					tfCode.setText("");
					tfCodeName.setText("");
					tfSpec.setText("");
					tfEa.setText("");
					tfPrice.setText("");
					tfAmt.setText("");
					
					tfCode.requestFocus();
				}
			});
			btnCancel.setBounds(215, 365, 68, 23);
		}
		return btnCancel;
	}
	public JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					String findStr = tfFindStr.getText();
					List<ProductVo> list = dao.select(findStr);
					model.setRowCount(0);
					for(ProductVo v : list) {
						model.addRow(v.getVector());
					}

					
				}
			});
			btnSearch.setBounds(865, 88, 72, 23);
		}
		return btnSearch;
	}
	public JTextField getTfFindStr() {
		if (tfFindStr == null) {
			tfFindStr = new JTextField();
			tfFindStr.setBounds(684, 89, 169, 21);
			tfFindStr.setColumns(10);
		}
		return tfFindStr;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(295, 120, 642, 428);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	public JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					
					int row = table.getSelectedRow();
					int col=0;
					String tempSno = (String)table.getValueAt(row, col);
					int sno = Integer.parseInt(tempSno);
					
					ProductVo vo = dao.selectOne(sno);
					setVo(vo);
					
					btnFind.setEnabled(false);
					btnSave.setEnabled(false);
					btnModify.setEnabled(true);
					btnDelete.setEnabled(true);
					
					
					
				}
			});
		}
		return table;
	}
	public JButton getBtnFind() {
		if (btnFind == null) {
			btnFind = new JButton("조회");
			btnFind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String code = tfCode.getText();
					ProductVo vo = dao.selectOne(code);
					if(vo != null) {
						tfCodeName.setText(vo.getCodeName());
						tfPrice.setText(vo.getPrice()+"");
						tfSpec.setText(vo.getSpec());
					}else {
						
						tfSno.setText("");
						tfCodeName.setText("");
						tfSpec.setText("");
						tfEa.setText("");
						tfPrice.setText("");
						tfAmt.setText("");
						
						ProductFrame.info.setText("자료가 없는뎅~~~~");
					}
					
				}
			});
			btnFind.setBounds(196, 119, 87, 23);
		}
		return btnFind;
	}
	public JTextField getTfSpec() {
		if (tfSpec == null) {
			tfSpec = new JTextField();
			tfSpec.setEditable(false);
			tfSpec.setBounds(104, 182, 179, 21);
			tfSpec.setColumns(10);
		}
		return tfSpec;
	}
	public JLabel getLblNewLabel_8() {
		if (lblNewLabel_8 == null) {
			lblNewLabel_8 = new JLabel("스펙");
			lblNewLabel_8.setBounds(27, 185, 57, 15);
		}
		return lblNewLabel_8;
	}
}
