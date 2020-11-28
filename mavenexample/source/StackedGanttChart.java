



import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.StandardGradientPaintTransformer;


public class StackedGanttChart extends ApplicationFrame {
	int size; //프로세스 개수
	ArrayList<Integer> squen = new ArrayList<Integer>(); // 전체 프로세스 순서 정보-색 입혀야 됨
	int countLapse=0;
	int[] proCount;//각 큐별로 몇 개의 분할을 가지고 있는지 확인
	Paint[] paint;
	//int countQueue=0;
	static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    public StackedGanttChart(final String title, int size) {
        super(title);
        this.size=size;
        proCount=new int[size+1];//0은 cpu, 다음부터 각 프로세스 대기큐 분할
        paint = new Color[size+1];
        
        //dataset.addValue(4, "CPU0", " ");
        //dataset.addValue(4, "0-0", " ");
        //dataset.addValue(3, "0-1", " ");
        //dataset.addValue(3, "CPU1", " ");
        
        //createDatasetReady(0, 0);
        //createDatasetR_Ceady(0, 0);
        //createDatasetComplete(3, 0);
        //createDatasetC_Ready(3, 0);
        //createDatasetR_Ceady(4, 0);
        
        createDatasetReady(0, 0);
        createDatasetR_Ceady(0, 0);
        createDatasetReady(1, 2);//
        createDatasetReady(2, 1);
        createDatasetC_Ready(3, 0);
        createDatasetR_Ceady(1, 1);
        createDatasetC_Ready(3, 1);
        createDatasetR_Ceady(3, 0);
        createDatasetComplete(1, 0);
        createDatasetR_Ceady(6, 2);//
        createDatasetComplete(2, 2);//
        createDatasetR_Ceady(3, 1);
        createDatasetComplete(1, 1);
        
        /*createDatasetR_Ceady(0, 2);
        createDatasetReady(5, 2);
        createDatasetComplete(5, 1);
        
        createDatasetR_Ceady(4, 2);
        createDatasetComplete(3, 2);*/
        final JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(912, 585));
        setContentPane(chartPanel);
    }
    
    /*private void createDataset() {
        

    	dataset.addValue(3, "CPU1", " ");
        //squen[count]=
    	dataset.addValue(3, "P2", " ");
    	dataset.addValue(4, "P3", " ");
    	dataset.addValue(2, "CPU2", " ");
    	dataset.addValue(4, "CPU3", " ");
    	dataset.addValue(2, "CPU4", " ");
    	dataset.addValue(1, "CPU5", " ");
    	dataset.addValue(3, "CPU6", " ");

    	dataset.addValue(3, "P1", " ");
    	dataset.addValue(3, "P4", " ");
        result.addValue(1, "P5", " ");
        result.addValue(3, "P6", " ");
    }*/
    public void createDatasetComplete(int lapse, int index) {//cpu에서 프로세스 끝남
    	dataset.addValue(lapse, "CPU"+String.valueOf(proCount[0]), " ");
    	squen.add(index);
    	countLapse++;
    	proCount[0]++;
    }
    public void createDatasetC_Ready(int lapse, int index) {//cpu에서 준비큐로
    	dataset.addValue(lapse, "CPU"+String.valueOf(proCount[0]), " ");//cpu에 할당
    	squen.add(index);
    	countLapse++;
    	proCount[0]++;
    	
    	dataset.addValue(lapse, String.valueOf(index)+"-"+String.valueOf(proCount[index+1]), " ");//준비큐에 빈 공간 할당
    	squen.add(size);//남은 공간 빈 색칠
    	countLapse++;
    	proCount[index+1]++;
    }
    public void createDatasetR_Ceady(int lapse, int index) {//준비큐에서 cpu로
    	dataset.addValue(lapse, String.valueOf(index)+"-"+String.valueOf(proCount[index+1]), " ");
    	squen.add(index);;
    	countLapse++;
    	proCount[index+1]++;
    	
    	//dataset.addValue(lapse, "CPU"+String.valueOf(proCount[0]), " ");
    	//squen.add(size);;//남은 cpu 공간 빈 색칠
    	//countLapse++;
    	//proCount[0]++;
    }
    public void createDatasetReady(int lapse, int index) {//큐에서 준비큐로
    	//if(cpuUse==false)->cpu에서의 빈공간 적재 추가
    	dataset.addValue(lapse, String.valueOf(index)+"-"+String.valueOf(proCount[index+1]), " ");
    	squen.add(size);;
    	countLapse++;
    	proCount[index+1]++;
    }


    private JFreeChart createChart(final CategoryDataset dataset) {

    for(int i=0;i<size;i++) {
    		int x=(int)(Math.random()*255);
    		int y=(int)(Math.random()*255);
    		int z=(int)(Math.random()*255);
    		paint[i]=new Color(x, y, z);
    	}//프로세스 개수와 같은 랜덤 색 생성
    	paint[size]=new Color(0,0,0,0);
    	//페인트 배열의 가장 마지막은 투명한 색.

    	//paint[0]=new Color(10,10,10);
    	//paint[1]=new Color(0,0,0,0);
        final JFreeChart chart = ChartFactory.createStackedBarChart(
            "OS scheduling",  // chart title
            "Category",                  // domain axis label
            "Time Wasted",                     // range axis label
            dataset,                     // data
            PlotOrientation.HORIZONTAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );
        
        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
        KeyToGroupMap map = new KeyToGroupMap("G0");
        for(int i=0;i<size+1;i++)//그룹화
        {
        	for(int i1=0;i1<proCount[i];i1++) {
        		if(i==0)
        			map.mapKeyToGroup("CPU"+String.valueOf(i1), "G0");
        		else
        			map.mapKeyToGroup(String.valueOf(i-1)+"-"+String.valueOf(i1), "G"+String.valueOf(i));
        	}
        	
        }
        //map.mapKeyToGroup("CPU0", "G0");
        //map.mapKeyToGroup("CPU1", "G0");
        //map.mapKeyToGroup("0-0", "G1");
        //map.mapKeyToGroup("0-1", "G1");
        /*map.mapKeyToGroup("CPU2", "G0");
        map.mapKeyToGroup("CPU3", "G1");
        map.mapKeyToGroup("CPU4", "G1");
        map.mapKeyToGroup("CPU5", "G1");
        map.mapKeyToGroup("CPU6", "G1");
        
        map.mapKeyToGroup("P1", "G2");
        map.mapKeyToGroup("P2", "G2");
        map.mapKeyToGroup("P3", "G2");
        map.mapKeyToGroup("P4", "G2");
        map.mapKeyToGroup("P5", "G2");
        map.mapKeyToGroup("P6", "G2");*/

        renderer.setSeriesToGroupMap(map); 
        for(int i=0;i<countLapse;i++) {//각 프로세스에 색을 칠해보자
        	renderer.setSeriesPaint(i, paint[squen.get(i)]);
        }
        //renderer.setSeriesPaint(0, paint[0]);
        //renderer.setSeriesPaint(1, paint[1]);
        //renderer.setSeriesPaint(2, paint[0]);
        //renderer.setSeriesPaint(3, paint[1]);
        renderer.setItemMargin(0.1);
        /*Paint p1 = new GradientPaint(
            0.0f, 0.0f, new Color(0x22, 0x22, 0xFF), 0.0f, 0.0f, new Color(0x88, 0x88, 0xFF)
        );
        renderer.setSeriesPaint(0, paint[0]);
        renderer.setSeriesPaint(4, paint[0]);
        renderer.setSeriesPaint(8, paint[0]);
         
        Paint p2 = new GradientPaint(
            0.0f, 0.0f, new Color(0x55, 0x12, 0x22), 0.0f, 0.0f, new Color(0x88, 0x98, 0x88)
        );
        renderer.setSeriesPaint(1, paint[1]); 
        renderer.setSeriesPaint(5, paint[1]); 
        renderer.setSeriesPaint(9, paint[1]); 
        Paint p5 = new Color(0xFF,0,0);
        Paint p3 = new GradientPaint(
            0.0f, 0.0f, new Color(0xFF, 0x22, 0x22), 0.0f, 0.0f, new Color(0xFF, 0x88, 0x88)
        );
        renderer.setSeriesPaint(2, p3);
        renderer.setSeriesPaint(6, p3);
        renderer.setSeriesPaint(10, p3);
            
        Paint p4 = new GradientPaint(
            0.0f, 0.0f, new Color(0xFF, 0xFF, 0x22,0), 0.0f, 0.0f, new Color(0xFF, 0xFF, 0x88,0)
        );//투명색. 아무것도 없는 경우 할당
        renderer.setSeriesPaint(3, p4);
        renderer.setSeriesPaint(7, p4);
        renderer.setSeriesPaint(11, p4);*/
        renderer.setGradientPaintTransformer(
            new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL)
        );
        
        SubCategoryAxis domainAxis = new SubCategoryAxis("Process");
        domainAxis.setCategoryMargin(0.1);//표 사이 간격
        domainAxis.addSubCategory("CPU");
        for(int i=0;i<size;i++) {
        	domainAxis.addSubCategory("ReadyQueue"+String.valueOf(i+1));
        }
        //domainAxis.addSubCategory("ReadyQueue1");
        //domainAxis.addSubCategory("Product 3");
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainAxis(domainAxis);
        //plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
        plot.setRenderer(renderer);
        plot.setFixedLegendItems(createLegendItems());
        return chart;
        
    }

    private LegendItemCollection createLegendItems() {//서브 창에 색칠
        LegendItemCollection result = new LegendItemCollection();
        for(int i=0;i<size;i++) {
        	LegendItem item = new LegendItem("P"+String.valueOf(i+1), paint[i]);
        	result.add(item);
        }
        /*LegendItem item1 = new LegendItem("P1", new Color(0x22, 0x22, 0xFF));
        LegendItem item2 = new LegendItem("P2", new Color(0x22, 0xFF, 0x22));
        LegendItem item3 = new LegendItem("P3", new Color(0xFF, 0x22, 0x22));
        result.add(item1);
        result.add(item2);
        result.add(item3);*/
        return result;
    }
    

    public static void main(final String[] args) {
        final StackedGanttChart demo = new StackedGanttChart("OS scheduling", 3);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        demo.setResizable(false);
    }

}