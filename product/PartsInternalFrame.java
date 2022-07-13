package db.product;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PartsInternalFrame extends JInternalFrame {
	PartsDao dao;
	DefaultTableModel model;
	DefaultTableCellRenderer cRender, rRender;//좌우정렬, 넓이 설정을 위해
	
	
	private JLabel lblNewLabel;
	private JTextField tfCode;
	private JTextField tfCodeName;
	private JTextField tfSpec;
	private JTextField tfPrice;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnSave;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnSearch;
	private JTextField tfFindStr;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PartsInternalFrame frame = new PartsInternalFrame();
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
	
	
	public PartsVo getVo() {
		String code = tfCode.getText();
		String codeName = tfCodeName.getText();
		String spec = tfSpec.getText();
		PartsVo vo = null;
		try {
			int price = Integer.parseInt(tfPrice.getText());
			vo = new PartsVo(code, codeName, spec, price);
		}catch(Exception ex) {
			ProductFrame.info.setText("금액을 확인해 보세요~");
		}
		
		return vo;
		
	}
	public PartsInternalFrame() {
		super("제품관리", true, true, true, true);
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBounds(100, 100, 926, 529);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getTfCode());
		getContentPane().add(getTfCodeName());
		getContentPane().add(getTfSpec());
		getContentPane().add(getTfPrice());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getLblNewLabel_2());
		getContentPane().add(getLblNewLabel_3());
		getContentPane().add(getLblNewLabel_4());
		getContentPane().add(getBtnSave());
		getContentPane().add(getBtnModify());
		getContentPane().add(getBtnDelete());
		getContentPane().add(getBtnSearch());
		getContentPane().add(getTfFindStr());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnCancel());
		
		setVisible(true);
		
		String[] header= {"No", "제품코드", "제품명", "스펙", "단가"};
		model = new DefaultTableModel(header, 0);
		table.setModel(model);
		
		// 좌우 정렬 설정
		cRender = new DefaultTableCellRenderer();
		cRender.setHorizontalAlignment(JLabel.CENTER);
		rRender = new DefaultTableCellRenderer();
		rRender.setHorizontalAlignment(JLabel.RIGHT);
		
		//넓이 설정
		table.getColumn("No").setPreferredWidth(14);
		table.getColumn("단가").setPreferredWidth(26);
		table.getColumn("제품코드").setPreferredWidth(20);
		
		// 오른쪽 정렬
		table.getColumn("단가").setCellRenderer(rRender);
		table.getColumn("제품코드").setCellRenderer(cRender);
		
		dao = new PartsDao();

	}
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("제품관리");
			lblNewLabel.setForeground(Color.MAGENTA);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
			lblNewLabel.setBounds(241, 10, 414, 36);
		}
		return lblNewLabel;
	}
	public JTextField getTfCode() {
		if (tfCode == null) {
			tfCode = new JTextField();
			tfCode.setBounds(81, 104, 116, 21);
			tfCode.setColumns(10);
		}
		return tfCode;
	}
	public JTextField getTfCodeName() {
		if (tfCodeName == null) {
			tfCodeName = new JTextField();
			tfCodeName.setBounds(81, 135, 147, 21);
			tfCodeName.setColumns(10);
		}
		return tfCodeName;
	}
	public JTextField getTfSpec() {
		if (tfSpec == null) {
			tfSpec = new JTextField();
			tfSpec.setBounds(81, 166, 170, 21);
			tfSpec.setColumns(10);
		}
		return tfSpec;
	}
	public JTextField getTfPrice() {
		if (tfPrice == null) {
			tfPrice = new JTextField();
			tfPrice.setBounds(81, 197, 116, 21);
			tfPrice.setColumns(10);
		}
		return tfPrice;
	}
	public JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("제품코드");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(12, 107, 57, 15);
		}
		return lblNewLabel_1;
	}
	public JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("제품명");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setBounds(12, 138, 57, 15);
		}
		return lblNewLabel_2;
	}
	public JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("제품스펙");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(12, 169, 57, 15);
		}
		return lblNewLabel_3;
	}
	public JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("단가");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(12, 200, 57, 15);
		}
		return lblNewLabel_4;
	}
	public JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("저장");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					PartsVo vo = getVo();
					
					/*
					String code = tfCode.getText();
					String codeName = tfCodeName.getText();
					String spec = tfSpec.getText();
					PartsVo vo = null;
					int price = Integer.parseInt(tfPrice.getText());
					vo = new PartsVo(code, codeName, spec, price);
					*/
					
					boolean b = dao.insert(vo);
					if(b) {
						ProductFrame.info.setText("제품 코드가 저장됨");
					}else {
						ProductFrame.info.setText("코드 저장중 오류 발생");
					}

					
				}
			});
			btnSave.setBounds(22, 228, 70, 23);
		}
		return btnSave;
	}
	public JButton getBtnModify() {
		if (btnModify == null) {
			btnModify = new JButton("수정");
			btnModify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					PartsVo vo = getVo();
					boolean b = dao.update(vo);
					if(b) {
						ProductFrame.info.setText("수정되었네요");
						btnSearch.doClick();
					}else {
						ProductFrame.info.setText("수정중 오류가 발생 되었네요");
					}
					
				}
			});
			btnModify.setBounds(102, 228, 70, 23);
		}
		return btnModify;
	}
	public JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					
					String code = tfCode.getText();
					boolean b = dao.delete(code);
					if(b) {
						ProductFrame.info.setText("삭제 되었네요");
						btnSearch.doClick();
						tfCode.setText("");
						tfCodeName.setText("");
						tfSpec.setText("");
						tfPrice.setText("");
						tfCode.requestFocus();
						
					}else {
						ProductFrame.info.setText("삭제중 오류가 발생 되었네요");
					}
					
					
				}
			});
			btnDelete.setBounds(181, 228, 70, 23);
		}
		return btnDelete;
	}
	public JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("조회");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					String findStr = tfFindStr.getText().trim();
					List<PartsVo> list = dao.select(findStr);
					model.setNumRows(0);
					
					for(PartsVo v : list) {
						model.addRow(v.getVector());
					}
					/*
					for(int i=0 ; i<list.size() ; i++) {
						PartsVo v2 = list.get(i);
						v2.setNo(i+1);
						
						model.addRow(v2.getVector());
					}
					*/
					
					
				}
			});
			btnSearch.setBounds(828, 64, 70, 23);
		}
		return btnSearch;
	}
	public JTextField getTfFindStr() {
		if (tfFindStr == null) {
			tfFindStr = new JTextField();
			tfFindStr.setBounds(623, 65, 193, 21);
			tfFindStr.setColumns(10);
		}
		return tfFindStr;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(367, 97, 531, 392);
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
					int col = 1;
					String code = (String)model.getValueAt(row, col);
					
					PartsVo vo = dao.selectOne(code);
					
					tfCode.setText(vo.getCode());
					tfCodeName.setText(vo.getCodeName());
					tfSpec.setText(vo.getSpec());
					tfPrice.setText(vo.getPrice()+"");
					
					tfCode.setEnabled(false);
					btnModify.setEnabled(true);
					btnDelete.setEnabled(true);
					btnSave.setEnabled(false);
					
				}
			});
		}
		return table;
	}
	public JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("취소");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					tfCode.setEnabled(true);
					btnModify.setEnabled(false);
					btnDelete.setEnabled(false);
					btnSave.setEnabled(true);
					tfCode.setText("");
					tfCode.requestFocus();
					
				}
			});
			btnCancel.setBounds(263, 228, 70, 23);
		}
		return btnCancel;
	}
}
