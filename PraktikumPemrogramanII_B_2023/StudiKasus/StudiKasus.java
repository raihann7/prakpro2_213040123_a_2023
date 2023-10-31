/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PraktikumPemrogramanII_B_2023.StudiKasus;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import PraktikumPemrogramanII_B_2023.StudiKasus.MyTableModel;

/**
 *
 * @author ahmad
 */

public class StudiKasus extends JFrame {
    
    public StudiKasus() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0); // Keluar dari aplikasi
                }
            }
        });

        JLabel labelInput = new JLabel("Nama :");
        labelInput.setBounds(15, 40, 350, 10);

        JLabel labelNoHP = new JLabel("Nomor HP :");
        labelNoHP.setBounds(15, 95, 350, 10);
        
        JLabel labelAlamat = new JLabel("Alamat :");
        labelAlamat.setBounds(15, 217, 350, 10);

        JTextField textField = new JTextField();
        textField.setBounds(15, 60, 350, 30);

        JTextField NoHP = new JTextField();
        NoHP.setBounds(15, 115, 350, 30);

        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15, 155, 350, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-laki", true);
        radioButton1.setBounds(15, 165, 350, 30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan", false);
        radioButton2.setBounds(15, 187, 350, 30);
        
        JTextArea alamatTextArea = new JTextArea();
        alamatTextArea.setBounds(15, 235, 350, 120);
        alamatTextArea.setLineWrap(true);
        alamatTextArea.setWrapStyleWord(true);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        JButton savebutton = new JButton("Simpan");
        savebutton.setBounds(15, 360, 100, 40);
        
        JButton editButton = new JButton("Edit");
        editButton.setBounds(120, 360, 100, 40);
        
        JButton hapusButton = new JButton("Hapus");
        hapusButton.setBounds(225, 360, 100, 40);

        JButton saveToFileButton = new JButton("Simpan ke File");
        saveToFileButton.setBounds(330, 360, 150, 40);

        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 405, 550, 150);
        
        MyTableModel tableModel = new MyTableModel(); // Inisialisasi model tabel
        table.setModel(tableModel);
        
        savebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textField.getText();
                String noHP = NoHP.getText();
                String jenisKelamin = radioButton1.isSelected() ? "Laki-laki" : "Perempuan";
                String alamat = alamatTextArea.getText();

                if (nama.isEmpty() || noHP.isEmpty() || alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua jawaban terlebih dahulu", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    int choice = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menyimpan data?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (choice == JOptionPane.YES_OPTION) {
                        tableModel.add(new ArrayList<>(Arrays.asList(nama, noHP, jenisKelamin, alamat)));
                    
                        textField.setText("");
                        NoHP.setText("");
                        alamatTextArea.setText("");
                    }
                }
            }
        });
        
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String nama = textField.getText();
                    String noHP = NoHP.getText();
                    String jenisKelamin = radioButton1.isSelected() ? "Laki-laki" : "Perempuan";
                    String alamat = alamatTextArea.getText();

                    if (nama.isEmpty() || noHP.isEmpty() || alamat.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Harap isi semua jawaban yang ingin diedit terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int choice = JOptionPane.showConfirmDialog(null, "Anda yakin ingin mengedit data?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (choice == JOptionPane.YES_OPTION) {
                            tableModel.updateRow(selectedRow, nama, noHP, jenisKelamin, alamat);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih baris yang akan diedit dari tabel terlebih dahulu", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        hapusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int choice = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (choice == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih baris yang akan dihapus dari tabel terlebih dahulu", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        saveToFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tableModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Tidak ada data untuk disimpan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Berkas Teks (.txt)", "txt");
                    fileChooser.setFileFilter(filter);

                    int userSelection = fileChooser.showSaveDialog(StudiKasus.this);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                            fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                        }

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                            String header = String.format("%-15s %-15s %-15s %-60s\n", "Nama", "Nomor HP", "Jenis Kelamin", "Alamat");
                            writer.write(header);

                            for (int i = 0; i < tableModel.getRowCount(); i++) {
                                String nama = tableModel.getValueAt(i, 0).toString();
                                String noHP = tableModel.getValueAt(i, 1).toString();
                                String jenisKelamin = tableModel.getValueAt(i, 2).toString();
                                String alamat = tableModel.getValueAt(i, 3).toString();
                                
                                String row = String.format("%-15s %-15s %-15s %-60s\n", nama, noHP, jenisKelamin, alamat);
                                writer.write(row);
                            }

                            JOptionPane.showMessageDialog(null, "Data berhasil disimpan ke file.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam menyimpan data ke file.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        
        this.add(savebutton);
        this.add(editButton);
        this.add(hapusButton);
        this.add(saveToFileButton);
        this.add(textField);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelInput);
        this.add(labelNoHP);
        this.add(NoHP);
        this.add(scrollableTable);
        this.add(labelAlamat);
        this.add(alamatTextArea);

        this.setSize(700, 850);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StudiKasus h = new StudiKasus();
                h.setVisible(true);
            }
        });
    }
}