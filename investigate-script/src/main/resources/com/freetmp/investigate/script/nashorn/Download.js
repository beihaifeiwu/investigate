var frame = new javax.swing.JFrame("JavaScript Based URL Fetcher");
var urlField = new javax.swing.JTextField(30);
var button = new javax.swing.JButton("Download");
var filechooser = new javax.swing.JFileChooser();
var row = javax.swing.Box.createHorizontalBox();
var col = javax.swing.Box.createVerticalBox();
var padding = new javax.swing.border.EmptyBorder(3, 3, 3, 3);

row.add(urlField);
row.add(button);
col.add(row);
frame.add(col);
row.setBorder(padding);
frame.pack();
frame.visible = true;

frame.addWindowListener(function (e, name) {
    if (name === "windowClosing") java.lang.System.exit(0);
});

button.addActionListener(function () {
    try {
        var url = new java.net.URL(urlField.text);
        var response = filechooser.showSaveDialog(frame);
        if (response != javax.swing.JFileChooser.APPROVE_OPTION) return;
        var file = filechooser.getSelectedFile();
        new java.lang.Thread(function () {
            download(url, file);
        }).start();
    } catch (e) {
        javax.swing.JOptionPane.showMessageDialog(frame,e.message,"Exception",javax.swing.JOptionPane.ERROR_MESSAGE);
    }
});


function download(url, file) {
    try{

        var row = new javax.swing.Box.createHorizontalBox();
        row.setBorder(padding);
        var label = url.toString() + ": ";
        row.add(new javax.swing.JLabel(label));
        var bar = new javax.swing.JProgressBar(0,100);
        bar.stringPainted = true;
        bar.string = file.toString();
        row.add(bar);
        col.add(row);
        frame.pack();

        bar.indeterminate = true;

        var conn = url.openConnection();
        conn.connect();
        var len = conn.contentLength;
        if(len){
            bar.maximum = len;
            bar.indeterminate = false;
        }

        var input = conn.inputStream;
        var output = new java.io.FileOutputStream(file);

        var buffer = java.lang.reflect.Array.newInstance(java.lang.Byte.TYPE,4096);
        var num;
        while((num=input.read(buffer)) != -1){
            output.write(buffer,0,num);
            bar.value += num;
        }
        output.close();
        input.close();

    }catch (e){
        if(bar){

        }
    }
}