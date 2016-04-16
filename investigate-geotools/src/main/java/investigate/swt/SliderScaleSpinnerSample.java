package investigate.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class SliderScaleSpinnerSample {

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    showSlider(shell);
    showScale(shell);
    showSpinner(shell);
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();

  }

  private static void showSlider(Shell shell) {
    shell.setLayout(new GridLayout());
    shell.setText("Slider");
    //创建一个标签显示当前所选中的值
    final Label sliderValue = new Label(shell, SWT.NONE);
    //创建滑块对象
    final Slider slider = new Slider(shell, SWT.HORIZONTAL);
    slider.setMaximum(100);//设置最大值为100
    slider.setMinimum(0);//设置最小值为0
    slider.setIncrement(1);//设置每次单击箭头时增长的值
    slider.setThumb(10);//设置滑块的大小
    slider.setPageIncrement(20);//设置单击滑块空白处一次移动的值
    slider.setSelection(50);
    sliderValue.setText("当前值为" + slider.getSelection());
    slider.addListener(SWT.Selection, event -> {
      String string = "SWT.NONE";
      switch (event.detail) {
        case SWT.DRAG:
          string = "SWT.DRAG";
          break;
        case SWT.HOME:
          string = "SWT.HOME";
          break;
        case SWT.END:
          string = "SWT.END";
          break;
        case SWT.ARROW_DOWN:
          string = "SWT.ARROW_DOWN";
          break;
        case SWT.ARROW_UP:
          string = "SWT.ARROW_UP";
          break;
        case SWT.PAGE_DOWN:
          string = "SWT.PAGE_DOWN";
          break;
        case SWT.PAGE_UP:
          string = "SWT.PAGE_UP";
          break;
      }
      System.out.println("触发的事件为 " + string);
      sliderValue.setText("当前值为" + slider.getSelection());
    });
    shell.pack();
    shell.open();
  }

  private static void showScale(Shell parent){
    Shell shell = new Shell (parent);
    shell.setLayout( new GridLayout());
    shell.setText("Scale");
    //创建一个标签显示当前所选中的值
    final Label sliderValue = new Label ( shell , SWT.NONE);
    //创建刻度条对象
    final Scale scale = new Scale (shell, SWT.HORIZONTAL);
    scale.setMaximum(100);//设置最大值为100
    scale.setMinimum(0);//设置最小值为0
    scale.setIncrement(1);//设置拖动滑块时增长的值
    scale.setPageIncrement(20);//设置单击滑块空白处一次移动的值
    scale.setSelection(50);//设置当前选中的值
    sliderValue.setText( "当前值为"+scale.getSelection());
    scale.addListener (SWT.Selection, event -> sliderValue.setText( "当前值为"+scale.getSelection()));
    shell.pack();
    shell.open ();
  }

  private static void showSpinner(Shell parent){
    Shell shell = new Shell (parent);
    shell.setLayout( new GridLayout());
    shell.setText("Spinner");
    //创建微调按钮对象
    final Spinner spinner = new Spinner (shell, SWT.NONE);
    spinner.setMaximum(100);//设置最大值为100
    spinner.setMinimum(0);//设置最小值为0
    spinner.setIncrement(1);//设置每次单击箭头时增长的值
    spinner.setPageIncrement(20);
    spinner.setSelection(50);//设置当前选中值
    spinner.setDigits(2);//设置小数点两位
    shell.pack();
    shell.open ();
  }
}
