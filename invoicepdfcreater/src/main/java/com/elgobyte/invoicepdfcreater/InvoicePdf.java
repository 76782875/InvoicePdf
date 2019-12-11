package com.elgobyte.invoicepdfcreater;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class InvoicePdf {

    //get application context
    private Context mContext;

    //Add paragraph to teh document
    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN.TIMES_ROMAN, 25.0f, Font.NORMAL, BaseColor.BLACK);
    private Font fDate = new Font(Font.FontFamily.TIMES_ROMAN.TIMES_ROMAN, 12.0f, Font.NORMAL, BaseColor.BLACK);
    private Font fName = new Font(Font.FontFamily.TIMES_ROMAN.TIMES_ROMAN, 35.0f, Font.NORMAL, BaseColor.BLACK);

    //************************************Array list for Voice Test Function**********************
    private static ArrayList<Integer> Amount = new ArrayList<>();
    // ***************************************************************************************** //

    private String stinvoiceName,
            stinvoiceDate,
            stinvoiceNumber,
            stsenderName,
            stsenderEmail,
            stsenderAddress,
            streverseName,
            streverseEmail,
            streverseAddress,
            stshortNote;

    private ArrayList<String> ardescription = new ArrayList<>();
    private ArrayList<String> arquantity = new ArrayList<>();
    private ArrayList<Integer> arprice = new ArrayList<>();
    private ArrayList<Integer> aramount = new ArrayList<>();

    private boolean dataSet = false;

    //Today Date
    private Date today = new Date();


    //*****************************************Crete constructor **************************************//
    public InvoicePdf(Activity context) {

        //get Application context
        this.mContext = context;

        //if the SKD < 23
        if (Build.VERSION.SDK_INT >= 23) {

            if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {

                //ask for permission!
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            } else {

                Log.d("Allow", " Permission");
                //Toast.makeText(context, "Premission Allow", Toast.LENGTH_SHORT).show();
                //pdf();

            }

        }

    }
    //************************************************************************************************//


    //***********************************Create a Test Invoice ***********************************//
    public void invoiceTest() {

        Amount.add(1);
        Amount.add(2);
        Amount.add(3);
        Amount.add(4);
        Amount.add(5);
        Amount.add(6);
        Amount.add(7);
        Amount.add(8);
        Amount.add(9);
        Amount.add(10);
        Amount.add(11);
        Amount.add(12);
        Amount.add(13);


        //Log.d("Allow", "pdfTest: ");
        Document mDoc = new Document();
        //File Name as a current time Name
        String mFileName = new SimpleDateFormat("dd MMM yyyy HH:mm:ss",
                Locale.getDefault()).format(System.currentTimeMillis());

        //pdf file path
        String FilePath = Environment.getExternalStorageDirectory() + "/" + "Test Table" + "\t \t" + mFileName + ".pdf";

        try {

            PdfWriter.getInstance(mDoc, new FileOutputStream(FilePath));

            //Open the Document the write file
            mDoc.open();

            //add author of the document (optional)
            mDoc.addAuthor("M Raheel");
            mDoc.addTitle("Simple Invoice");
            mDoc.addSubject("For Invoice Record manage");
            mDoc.setPageSize(PageSize.A4);

           /* //Add paragraph to teh document
            Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN.TIMES_ROMAN, 25.0f, Font.NORMAL, BaseColor.BLACK);
            Font fDate = new Font(Font.FontFamily.TIMES_ROMAN.TIMES_ROMAN, 12.0f, Font.NORMAL, BaseColor.BLACK);
            Font fName = new Font(Font.FontFamily.TIMES_ROMAN.TIMES_ROMAN, 35.0f, Font.NORMAL, BaseColor.BLACK);*/

            //************************************* Create paragraph to add the cell *************************** //
            Paragraph invoice = new Paragraph("Invoice", fName);
            invoice.setAlignment(Element.ALIGN_RIGHT);

            Paragraph Date = new Paragraph("DATE: | " + toDate(today.getTime()), fDate);
            Date.setAlignment(Element.ALIGN_RIGHT);

            Paragraph invoiceNumber = new Paragraph("INVOICE # 12345", fDate);
            invoiceNumber.setAlignment(Element.ALIGN_RIGHT);

            Paragraph from = new Paragraph("FROM:   Content Arcade", fDate);
            from.setAlignment(Element.ALIGN_LEFT);

            Paragraph too = new Paragraph("TO:       Client Name", fDate);
            too.setAlignment(Element.ALIGN_RIGHT);

            Paragraph companyEmail = new Paragraph("Email:     contentarcade@gamil.com", fDate);
            companyEmail.setAlignment(Element.ALIGN_LEFT);

            Paragraph clientEmail = new Paragraph("Email:    client@gamil.com", fDate);
            clientEmail.setAlignment(Element.ALIGN_RIGHT);

            Paragraph companyAddress = new Paragraph("Address:  Faisalabad", fDate);
            companyAddress.setAlignment(Element.ALIGN_LEFT);

            Paragraph clientAddress = new Paragraph("Address: Faisalabad", fDate);
            clientAddress.setAlignment(Element.ALIGN_RIGHT);

            Paragraph note = new Paragraph("Notes:", fTitle);
            note.setAlignment(Element.ALIGN_CENTER);

            Paragraph noteRead = new Paragraph("Enter note and other special consideration here \n \n \n \n \n \n \n \n", fDate);
            noteRead.setAlignment(Element.ALIGN_CENTER);


            //************************************************************************************************//


            //********************************** Create Table & formatting ******************************//

            //Space table
            PdfPTable spaceTable = new PdfPTable(1);
            spaceTable.setWidthPercentage(100);
            spaceTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Name table
            PdfPTable invoiceTable = new PdfPTable(1);
            invoiceTable.setWidthPercentage(100);
            invoiceTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Date table
            PdfPTable dateTable = new PdfPTable(1);
            dateTable.setWidthPercentage(100);
            dateTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Invoice number table
            PdfPTable invoiceNumberTable = new PdfPTable(1);
            invoiceNumberTable.setWidthPercentage(100);
            invoiceNumberTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //From & to Table
            PdfPTable fromToTable = new PdfPTable(2);
            fromToTable.setWidthPercentage(100);
            fromToTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //From & to Table
            PdfPTable emailTable = new PdfPTable(2);
            emailTable.setWidthPercentage(100);
            emailTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Address Table
            PdfPTable addressTable = new PdfPTable(2);
            addressTable.setWidthPercentage(100);
            addressTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Add note
            PdfPTable noteTable = new PdfPTable(1);
            addressTable.setWidthPercentage(100);
            addressTable.setHorizontalAlignment(Element.ALIGN_LEFT);

            //Add note Read
            PdfPTable noteReadTable = new PdfPTable(1);
            noteReadTable.setWidthPercentage(90);
            noteReadTable.setHorizontalAlignment(Element.ALIGN_RIGHT);


            // ******************************************************************************************* //


            // *********************************Add new Cell in the table***********************************//
            PdfPCell date, name, invoice_num, fromCell, toCell, comEmailCell, clientEmailCell, comAddressCell, cliAddressCelll;

            PdfPCell spaceCell, noteCell, noteReadCell;
            //*********************************************************************************************//


            //***********************************Add new Column in the table********************************//
            //Add new Space
            spaceCell = new PdfPCell(new Paragraph("\n"));
            spaceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            spaceCell.setBorder(Rectangle.NO_BORDER);
            spaceCell.setColspan(1);


            //Add new name
            name = new PdfPCell(new Paragraph(invoice));
            name.setHorizontalAlignment(Element.ALIGN_RIGHT);
            name.setBorder(Rectangle.NO_BORDER);
            name.setColspan(1);

            //Add new Column in the Second line in the Pdf
            date = new PdfPCell(new Paragraph(Date));
            date.setHorizontalAlignment(Element.ALIGN_RIGHT);
            date.setBorder(Rectangle.NO_BORDER);
            date.setColspan(1);

            //Add new Invoice number in the Pdf
            invoice_num = new PdfPCell(new Paragraph(invoiceNumber));
            invoice_num.setHorizontalAlignment(Element.ALIGN_RIGHT);
            invoice_num.setBorder(Rectangle.NO_BORDER);
            invoice_num.setColspan(1);

            // Add new From
            fromCell = new PdfPCell(new Paragraph(from));
            fromCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            fromCell.setBorder(Rectangle.NO_BORDER);
            fromCell.setColspan(1);

            //Add new Too send
            toCell = new PdfPCell(new Paragraph(too));
            toCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toCell.setBorder(Rectangle.NO_BORDER);
            toCell.setColspan(1);

            //Add new Data in the Company Cell email
            comEmailCell = new PdfPCell(new Paragraph(companyEmail));
            comEmailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            comEmailCell.setBorder(Rectangle.NO_BORDER);
            comEmailCell.setColspan(1);

            //Add new Data in the Company the
            clientEmailCell = new PdfPCell(new Paragraph(clientEmail));
            clientEmailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            clientEmailCell.setBorder(Rectangle.NO_BORDER);
            clientEmailCell.setColspan(1);

            //Add company address
            comAddressCell = new PdfPCell(new Paragraph(companyAddress));
            comAddressCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            comAddressCell.setBorder(Rectangle.NO_BORDER);
            comAddressCell.setColspan(1);

            //Add Client address
            cliAddressCelll = new PdfPCell(new Paragraph(clientAddress));
            cliAddressCelll.setHorizontalAlignment(Element.ALIGN_LEFT);
            cliAddressCelll.setBorder(Rectangle.NO_BORDER);
            cliAddressCelll.setColspan(1);

            //Add para in note
            noteCell = new PdfPCell(new Paragraph(note));
            noteCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            noteCell.setBorder(Rectangle.NO_BORDER);
            noteCell.setColspan(1);

            //Add note Read Cell
            noteReadCell = new PdfPCell(new Paragraph(noteRead));
            noteReadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            noteReadCell.setVerticalAlignment(Element.ALIGN_TOP);
            noteReadCell.setColspan(1);


            //**********************************************************************************************


            //*************************************Add in cell in the table*****************************//

            spaceTable.addCell(spaceCell);

            invoiceTable.addCell(name);
            dateTable.addCell(date);
            invoiceNumberTable.addCell(invoice_num);
            fromToTable.addCell(fromCell);
            fromToTable.addCell(toCell);
            emailTable.addCell(comEmailCell);
            emailTable.addCell(clientEmailCell);
            addressTable.addCell(comAddressCell);
            addressTable.addCell(cliAddressCelll);
            noteTable.addCell(noteCell);
            noteReadTable.addCell(noteReadCell);

            // *****************************************************************************************//

            //***********************************Add table in the document********************************//

            mDoc.add(invoiceTable);
            mDoc.add(spaceTable);
            mDoc.add(dateTable);
            mDoc.add(invoiceNumberTable);
            mDoc.add(spaceTable);
            mDoc.add(fromToTable);
            mDoc.add(emailTable);
            mDoc.add(addressTable);
            mDoc.add(spaceTable);

            // ******************************************************************************************//


            //Method for data input in the table
            mDoc.add(createFirstTestTable());

            mDoc.add(spaceTable);

            mDoc.add(noteTable);

            mDoc.add(spaceTable);

            mDoc.add(noteReadTable);

            //Add Notes in the Table


            mDoc.close();
            Toast.makeText(mContext, "Invoice Save", Toast.LENGTH_SHORT).show();


        } catch (Exception ex) {

            Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    //*********************************************************************************************//


    //***********************************Crete first test Table **************************************//
    private static PdfPTable createFirstTestTable() {


        PdfPCell description, amount, quantity, price, desCell, quantityCell, priceCell, AmountShow, Space, TotalAmount, subtotal;

        Integer total_amount = 0;

        //Add paragraph to teh document
        Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN.TIMES_ROMAN, 14.0f, Font.NORMAL, BaseColor.BLACK);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        //table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        //Add new Column in the table
        desCell = new PdfPCell(new Paragraph("Item Description", fTitle));
        quantityCell = new PdfPCell(new Paragraph("Quantity ", fTitle));
        priceCell = new PdfPCell(new Paragraph("Price ", fTitle));
        AmountShow = new PdfPCell(new Paragraph("Amount", fTitle));


        desCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        priceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        AmountShow.setHorizontalAlignment(Element.ALIGN_CENTER);

        desCell.setVerticalAlignment(Element.ALIGN_CENTER);
        quantityCell.setVerticalAlignment(Element.ALIGN_CENTER);
        priceCell.setVerticalAlignment(Element.ALIGN_CENTER);
        AmountShow.setVerticalAlignment(Element.ALIGN_CENTER);


        desCell.setColspan(2);
        quantityCell.setColspan(1);
        priceCell.setColspan(1);
        AmountShow.setColspan(1);

        table.addCell(desCell);
        table.addCell(quantityCell);
        table.addCell(priceCell);
        table.addCell(AmountShow);

        for (int i = 1; i < Amount.size(); i++) {

            description = new PdfPCell(new Paragraph("Description: " + (i)));
            quantity = new PdfPCell(new Paragraph("Quantity: " + (i)));
            price = new PdfPCell(new Paragraph(String.valueOf(i * 150)));
            amount = new PdfPCell(new Paragraph("$" + (i)));


            total_amount += Amount.get(i);

            description.setHorizontalAlignment(Element.ALIGN_CENTER);
            amount.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
            price.setHorizontalAlignment(Element.ALIGN_CENTER);


            description.setColspan(2);
            quantity.setColspan(1);
            price.setColspan(1);
            amount.setColspan(1);


            description.setBorder(Rectangle.NO_BORDER);
            amount.setBorder(Rectangle.NO_BORDER);
            quantity.setBorder(Rectangle.NO_BORDER);
            price.setBorder(Rectangle.NO_BORDER);

            description.setBorderWidthLeft(1);
            description.setBorderWidthRight(1);
            quantity.setBorderWidthRight(1);
            price.setBorderWidthRight(1);
            amount.setBorderWidthRight(1);


            table.addCell(description);
            table.addCell(quantity);
            table.addCell(price);
            table.addCell(amount);


        }

        Space = new PdfPCell(new Paragraph());
        Space.setColspan(3);
        Space.setBorder(Rectangle.NO_BORDER);
        Space.setBorderWidthTop(1);

        subtotal = new PdfPCell(new Paragraph(("Subtotal")));
        subtotal.setColspan(1);
        subtotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        subtotal.setBorder(Rectangle.NO_BORDER);
        subtotal.setBorderWidthTop(1);

        TotalAmount = new PdfPCell(new Paragraph(("Total = " + total_amount)));
        TotalAmount.setColspan(1);
        TotalAmount.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(Space);
        table.addCell(subtotal);
        table.addCell(TotalAmount);

        return table;

        //Toast.makeText(, "", Toast.LENGTH_SHORT).show();


    }
    //************************************************************************************************//


    //***************************************Get date without Time*************************************//
    @SuppressLint("SimpleDateFormat")
    private String toDate(long timestamp) {

        Date date = new Date(timestamp * 1000);
        return new SimpleDateFormat("MMM-dd-yy").format(date);
    }
    //*************************************************************************************************//


    //***************************Method for get data from user to create pdf****************************//
    public void setData(String invoiceName, String invoiceDate, String invoiceNumber, String senderName, String senderEmail,
                        String senderAddress, String reverseName, String reverseEmail, String reverseAddress,
                        ArrayList<String> description, ArrayList<String> quantity, ArrayList<Integer> price,
                        ArrayList<Integer> amount, String shortNote) {

        if (description.size() == quantity.size() && price.size() == amount.size()) {

            dataSet = true;

            stinvoiceName = invoiceName;
            stinvoiceDate = invoiceDate;
            stinvoiceNumber = invoiceNumber;
            stsenderName = senderName;
            stsenderEmail = senderEmail;
            stsenderAddress = senderAddress;
            streverseName = reverseName;
            streverseEmail = reverseEmail;
            streverseAddress = reverseAddress;
            ardescription.addAll(description);
            arquantity.addAll(quantity);
            arprice.addAll(price);
            aramount.addAll(amount);
            stshortNote = shortNote;


        } else {

            Toast.makeText(mContext, "Plz enter the same length  of array list", Toast.LENGTH_SHORT).show();
        }


    }


    //*************************************************************************************************//


    //****************************************Create PDF**********************************//
    public void createPdf() {

        if (dataSet) {

            //Method call to create pdf
            invoiceCreate(stinvoiceName, stinvoiceDate, stinvoiceNumber, stsenderName, stsenderEmail,
                    stsenderAddress, streverseName, streverseEmail, streverseAddress,
                    ardescription, arquantity, arprice,
                    aramount, stshortNote);

        } else {

            Toast.makeText(mContext, "Plz set data then calling", Toast.LENGTH_SHORT).show();
        }
    }
    //************************************************************************************//

    //********************* Create pdf if values get from the user ************************************//
    private void invoiceCreate(String invoiceName, String invoiceDate, String invoiceNumber,
                               String senderName, String senderEmail, String senderAddress,
                               String reverseName, String reverseEmail, String reverseAddress,
                               ArrayList<String> description, ArrayList<String> quantity,
                               ArrayList<Integer> price, ArrayList<Integer> amount,
                               String shortNote) {

        //Log.d("Allow", "pdfTest: ");
        Document mDoc = new Document();

        //File Name as a current time Name
        String mFileName = new SimpleDateFormat("dd MMM yyyy HH:mm:ss",
                Locale.getDefault()).format(System.currentTimeMillis());

        //pdf file path
        String FilePath = Environment.getExternalStorageDirectory() + "/" + invoiceName + "\t \t" + mFileName + ".pdf";

        try {

            PdfWriter.getInstance(mDoc, new FileOutputStream(FilePath));

            //Open the Document the write file
            mDoc.open();

            //add author of the document (optional)
            mDoc.addAuthor("M Raheel");
            mDoc.addTitle("Simple Invoice");
            mDoc.addSubject("For Invoice Record manage");
            mDoc.setPageSize(PageSize.A4);


            //************************************* Create paragraph to add the cell *************************** //
            Paragraph invoice = new Paragraph("Invoice", fName);
            invoice.setAlignment(Element.ALIGN_RIGHT);

            Paragraph Date = new Paragraph("DATE: |" + invoiceDate, fDate);
            Date.setAlignment(Element.ALIGN_RIGHT);

            Paragraph invoice_Number = new Paragraph("INVOICE #" + invoiceNumber, fDate);
            invoice_Number.setAlignment(Element.ALIGN_RIGHT);

            Paragraph from = new Paragraph("FROM:   " + senderName, fDate);
            from.setAlignment(Element.ALIGN_LEFT);

            Paragraph too = new Paragraph("TO:         " + reverseName, fDate);
            too.setAlignment(Element.ALIGN_RIGHT);

            Paragraph companyEmail = new Paragraph("Email:     " + senderEmail, fDate);
            companyEmail.setAlignment(Element.ALIGN_LEFT);

            Paragraph clientEmail = new Paragraph("Email:     " + reverseEmail, fDate);
            clientEmail.setAlignment(Element.ALIGN_RIGHT);

            Paragraph companyAddress = new Paragraph("Address:  " + senderAddress, fDate);
            companyAddress.setAlignment(Element.ALIGN_LEFT);

            Paragraph clientAddress = new Paragraph("Address:  " + reverseAddress, fDate);
            clientAddress.setAlignment(Element.ALIGN_RIGHT);

            Paragraph note = new Paragraph("Notes:", fTitle);
            note.setAlignment(Element.ALIGN_CENTER);

            Paragraph noteRead = new Paragraph(shortNote + " \n \n \n \n \n \n \n \n", fDate);
            noteRead.setAlignment(Element.ALIGN_CENTER);


            //************************************************************************************************//


            //********************************** Create Table & formatting ******************************//

            //Space table
            PdfPTable spaceTable = new PdfPTable(1);
            spaceTable.setWidthPercentage(100);
            spaceTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Name table
            PdfPTable invoiceTable = new PdfPTable(1);
            invoiceTable.setWidthPercentage(100);
            invoiceTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Date table
            PdfPTable dateTable = new PdfPTable(1);
            dateTable.setWidthPercentage(100);
            dateTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Invoice number table
            PdfPTable invoiceNumberTable = new PdfPTable(1);
            invoiceNumberTable.setWidthPercentage(100);
            invoiceNumberTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //From & to Table
            PdfPTable fromToTable = new PdfPTable(2);
            fromToTable.setWidthPercentage(100);
            fromToTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //From & to Table
            PdfPTable emailTable = new PdfPTable(2);
            emailTable.setWidthPercentage(100);
            emailTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Address Table
            PdfPTable addressTable = new PdfPTable(2);
            addressTable.setWidthPercentage(100);
            addressTable.setHorizontalAlignment(Element.ALIGN_CENTER);

            //Add note
            PdfPTable noteTable = new PdfPTable(1);
            addressTable.setWidthPercentage(100);
            addressTable.setHorizontalAlignment(Element.ALIGN_LEFT);

            //Add note Read
            PdfPTable noteReadTable = new PdfPTable(1);
            noteReadTable.setWidthPercentage(90);
            noteReadTable.setHorizontalAlignment(Element.ALIGN_RIGHT);


            // ******************************************************************************************* //


            // *********************************Add new Cell in the table***********************************//
            PdfPCell date, name, invoice_num, fromCell, toCell, comEmailCell, clientEmailCell, comAddressCell, cliAddressCelll;

            PdfPCell spaceCell, noteCell, noteReadCell;
            //*********************************************************************************************//


            //***********************************Add new Column in the table********************************//
            //Add new Space
            spaceCell = new PdfPCell(new Paragraph("\n"));
            spaceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            spaceCell.setBorder(Rectangle.NO_BORDER);
            spaceCell.setColspan(1);


            //Add new name
            name = new PdfPCell(new Paragraph(invoice));
            name.setHorizontalAlignment(Element.ALIGN_RIGHT);
            name.setBorder(Rectangle.NO_BORDER);
            name.setColspan(1);

            //Add new Column in the Second line in the Pdf
            date = new PdfPCell(new Paragraph(Date));
            date.setHorizontalAlignment(Element.ALIGN_RIGHT);
            date.setBorder(Rectangle.NO_BORDER);
            date.setColspan(1);

            //Add new Invoice number in the Pdf
            invoice_num = new PdfPCell(new Paragraph(invoice_Number));
            invoice_num.setHorizontalAlignment(Element.ALIGN_RIGHT);
            invoice_num.setBorder(Rectangle.NO_BORDER);
            invoice_num.setColspan(1);

            // Add new From
            fromCell = new PdfPCell(new Paragraph(from));
            fromCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            fromCell.setBorder(Rectangle.NO_BORDER);
            fromCell.setColspan(1);

            //Add new Too send
            toCell = new PdfPCell(new Paragraph(too));
            toCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            toCell.setBorder(Rectangle.NO_BORDER);
            toCell.setColspan(1);

            //Add new Data in the Company Cell email
            comEmailCell = new PdfPCell(new Paragraph(companyEmail));
            comEmailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            comEmailCell.setBorder(Rectangle.NO_BORDER);
            comEmailCell.setColspan(1);

            //Add new Data in the Company the
            clientEmailCell = new PdfPCell(new Paragraph(clientEmail));
            clientEmailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            clientEmailCell.setBorder(Rectangle.NO_BORDER);
            clientEmailCell.setColspan(1);

            //Add company address
            comAddressCell = new PdfPCell(new Paragraph(companyAddress));
            comAddressCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            comAddressCell.setBorder(Rectangle.NO_BORDER);
            comAddressCell.setColspan(1);

            //Add Client address
            cliAddressCelll = new PdfPCell(new Paragraph(clientAddress));
            cliAddressCelll.setHorizontalAlignment(Element.ALIGN_LEFT);
            cliAddressCelll.setBorder(Rectangle.NO_BORDER);
            cliAddressCelll.setColspan(1);

            //Add para in note
            noteCell = new PdfPCell(new Paragraph(note));
            noteCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            noteCell.setBorder(Rectangle.NO_BORDER);
            noteCell.setColspan(1);

            //Add note Read Cell
            noteReadCell = new PdfPCell(new Paragraph(noteRead));
            noteReadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            noteReadCell.setVerticalAlignment(Element.ALIGN_TOP);
            noteReadCell.setColspan(1);


            //**********************************************************************************************


            //*************************************Add in cell in the table*****************************//

            spaceTable.addCell(spaceCell);

            invoiceTable.addCell(name);
            dateTable.addCell(date);
            invoiceNumberTable.addCell(invoice_num);
            fromToTable.addCell(fromCell);
            fromToTable.addCell(toCell);
            emailTable.addCell(comEmailCell);
            emailTable.addCell(clientEmailCell);
            addressTable.addCell(comAddressCell);
            addressTable.addCell(cliAddressCelll);
            noteTable.addCell(noteCell);
            noteReadTable.addCell(noteReadCell);

            // *****************************************************************************************//

            //***********************************Add table in the document********************************//

            mDoc.add(invoiceTable);
            mDoc.add(spaceTable);
            mDoc.add(dateTable);
            mDoc.add(invoiceNumberTable);
            mDoc.add(spaceTable);
            mDoc.add(fromToTable);
            mDoc.add(emailTable);
            mDoc.add(addressTable);
            mDoc.add(spaceTable);

            // ******************************************************************************************//


            //Method for data input in the table
            mDoc.add(createTable(description, quantity, price, amount));

            mDoc.add(spaceTable);

            mDoc.add(noteTable);

            mDoc.add(spaceTable);

            mDoc.add(noteReadTable);

            //Add Notes in the Table


            mDoc.close();
            Toast.makeText(mContext, "Invoice Save", Toast.LENGTH_SHORT).show();


        } catch (Exception ex) {

            Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    //*************************************************************************************************//


    //*************************************Method for data input in the table***********************************//
    private static PdfPTable createTable(ArrayList<String> description, ArrayList<String> quantity,
                                         ArrayList<Integer> price, ArrayList<Integer> amount) {


        PdfPCell descriptionCell, amountCell, quantityCellPdf, priceCellPDF, desCell, quantityCell, priceCell, AmountShow, Space, TotalAmount, subtotal;

        Integer total_amount = 0;

        //Add paragraph to teh document
        Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN.TIMES_ROMAN, 14.0f, Font.NORMAL, BaseColor.BLACK);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        //table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        //Add new Column in the table
        desCell = new PdfPCell(new Paragraph("Item Description", fTitle));
        quantityCellPdf = new PdfPCell(new Paragraph("Quantity ", fTitle));
        priceCellPDF = new PdfPCell(new Paragraph("Price ", fTitle));
        AmountShow = new PdfPCell(new Paragraph("Amount", fTitle));


        desCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        quantityCellPdf.setHorizontalAlignment(Element.ALIGN_CENTER);
        priceCellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);
        AmountShow.setHorizontalAlignment(Element.ALIGN_CENTER);

        desCell.setVerticalAlignment(Element.ALIGN_CENTER);
        quantityCellPdf.setVerticalAlignment(Element.ALIGN_CENTER);
        priceCellPDF.setVerticalAlignment(Element.ALIGN_CENTER);
        AmountShow.setVerticalAlignment(Element.ALIGN_CENTER);


        desCell.setColspan(2);
        quantityCellPdf.setColspan(1);
        priceCellPDF.setColspan(1);
        AmountShow.setColspan(1);

        table.addCell(desCell);
        table.addCell(quantityCellPdf);
        table.addCell(priceCellPDF);
        table.addCell(AmountShow);

        for (int i = 0; i < amount.size(); i++) {

            descriptionCell = new PdfPCell(new Paragraph(description.get(i)));
            quantityCellPdf = new PdfPCell(new Paragraph(quantity.get(i)));
            priceCellPDF = new PdfPCell(new Paragraph(String.valueOf(price.get(i))));
            amountCell = new PdfPCell(new Paragraph("$" + (amount.get(i))));


            total_amount += amount.get(i);

            descriptionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            amountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantityCellPdf.setHorizontalAlignment(Element.ALIGN_CENTER);
            priceCellPDF.setHorizontalAlignment(Element.ALIGN_CENTER);


            descriptionCell.setColspan(2);
            quantityCellPdf.setColspan(1);
            priceCellPDF.setColspan(1);
            amountCell.setColspan(1);


            descriptionCell.setBorder(Rectangle.NO_BORDER);
            amountCell.setBorder(Rectangle.NO_BORDER);
            quantityCellPdf.setBorder(Rectangle.NO_BORDER);
            priceCellPDF.setBorder(Rectangle.NO_BORDER);

            descriptionCell.setBorderWidthLeft(1);
            descriptionCell.setBorderWidthRight(1);
            quantityCellPdf.setBorderWidthRight(1);
            priceCellPDF.setBorderWidthRight(1);
            amountCell.setBorderWidthRight(1);


            table.addCell(descriptionCell);
            table.addCell(quantityCellPdf);
            table.addCell(priceCellPDF);
            table.addCell(amountCell);


        }

        Space = new PdfPCell(new Paragraph());
        Space.setColspan(3);
        Space.setBorder(Rectangle.NO_BORDER);
        Space.setBorderWidthTop(1);

        subtotal = new PdfPCell(new Paragraph(("Subtotal")));
        subtotal.setColspan(1);
        subtotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        subtotal.setBorder(Rectangle.NO_BORDER);
        subtotal.setBorderWidthTop(1);

        TotalAmount = new PdfPCell(new Paragraph(("Total = " + total_amount)));
        TotalAmount.setColspan(1);
        TotalAmount.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(Space);
        table.addCell(subtotal);
        table.addCell(TotalAmount);

        return table;

        //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
    }
    //************************************************************************************************************//


}
