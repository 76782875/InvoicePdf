# InvoicePdf

# Step 1. Add the JitPack repository to your build file
 
    allprojects {
	   	repositories {
			...
			maven { url 'https://jitpack.io' }
		   }
    }

# Step 2. Add the dependency
 
    dependencies {
	        implementation 'com.github.mraheel48:InvoicePdf:1.0.1'
     }

# Step 3. Create new object of the Invoice PDF

        InvoicePdf obj = new InvoicePdf(MainActivity.this);
        //Note! if the SKD < 23 it take a uses-permission otherwise  it can't working
        
# Step 4. Send data to setData Method...

                        obj.setData("Pdf_Name", "Pdf_Date",
                        "Invoice #", "Sender_Name", "Sender_Email"
                        , "Sender_Address", "Receiver",
                        "Receiver_Email", "Receiver_Address",
                        description, quantity, price, amount, "this is the Short note to Save PDF");
                        
                        //Note description, quantity, price, amount is array list with same Size to print table
                        //& price, amount also Integer..
                        
# Step 5. Create pdf Call
     
     obj.createPdf();
     //Your File is Save directory (/storage/InvoicePdf/).... :-)
