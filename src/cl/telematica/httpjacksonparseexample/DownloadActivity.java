package cl.telematica.httpjacksonparseexample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.telematica.httpjacksonparseexample.adapters.RssAdapter_details;
import cl.telematica.httpjacksonparseexample.asynctask.DownloadManager;
import cl.telematica.httpjacksonparseexample.interfaces.DownloadListener;
import cl.telematica.httpjacksonparseexample.models.EventModel;
import cl.telematica.httpjacksonparseexample.models.FirstNode;
import cl.telematica.httpjacksonparseexample.models.PerformanceModel;
import cl.telematica.httpjacksonparseexample.models.ResultsModel;
import cl.telematica.httpjacksonparseexample.models.ResultsPageModel;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DownloadActivity extends Activity implements DownloadListener {
	
	private ProgressBar progressBar;
	private TextView text;
	private ListView listView;
	private List <PerformanceModel> list = new ArrayList<PerformanceModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);

//		setContentView(R.layout.performance_activity);
//		listView = (ListView) findViewById(R.id.listView3);
//		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		text = (TextView) findViewById(R.id.textView1);
		
		new DownloadManager(this, 10000, 15000, "GET")
					.execute(getString(R.string.page_url));
	}

	@Override
	public void onRequestStart() {
		if(progressBar.getVisibility() == View.GONE)
			progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void onRequestComplete(String data) {
		if(progressBar.getVisibility() == View.VISIBLE){
			progressBar.setVisibility(View.GONE);
		}
	/*FirstNode model = (FirstNode) parseServiceResponse(data, FirstNode.class);
		if (model != null && model.resultsPageModel != null) {
			if (model.resultsPageModel.results != null) {
				ResultsModel resultModel = model.resultsPageModel.results;
				if (resultModel != null) {
					List<EventModel> eventsList = resultModel.eventList;
					if (eventsList != null && eventsList.size() > 0) {
						for (EventModel event : eventsList) {
							List<PerformanceModel> performanceList = event.performanceList;
							if(performanceList != null && performanceList.size()>0){
							*/
								
								//for(PerformanceModel performance : performanceList)
						/*			textToShow = "Resultado: \n\n" + "billing: "
											+ performance.billing + "\n\n" + "billing index: " + performance.billingIndex
											+ "\n\n" + "nombre: " + performance.displayName
											+ "\n\n" + "id: " + performance.id;
*/
									//EarthQuakeDataModel model = new EarthQuakeDataModel();
	//								PerformanceModel performance = new PerformanceModel(); 
//									performance.billing = performanceList.get(0).billing;
//									performance.billingIndex = performanceList.get(0).billingIndex;
//									performance.displayName = performanceList.get(0).displayName;
//									performance.id = performanceList.get(0).id;
		//							list.add(performance);
									
	//							}
						/*	textToShow = "Resultado: \n\n" + "nombre: "
									+ event.displayName + "\n\n" + "popularidad: " + event.popularity
									+ "\n\n" + "status: " + event.status
									+ "\n\n" + "tipo: " + event.type;*/
							
/*							}
					}
				}
			}
		}
	*/	
    
	PerformanceModel model = new PerformanceModel(); 
	model.billing = "asdf";
	model.billingIndex = 1;
	model.displayName = "hola";
	model.id = 3;
	String textToShow = "";

	    list.add(model);
	
		textToShow = list.get(0).billing + " " + list.get(0).displayName + " " + list.get(0).id; 
		text.setText(textToShow);
	//	RssAdapter_details adapter = new RssAdapter_details(getApplicationContext(), R.string.app_name, list);
	//	listView.setAdapter(adapter);
	}

	@Override
	public void onError(String error, int code) {
		if(progressBar.getVisibility() == View.VISIBLE)
			progressBar.setVisibility(View.GONE);
		text.setText(error);
	}	
	
	/**
	 * Parses the WebResponse.
	 * 
	 * @param reader
	 * @param expectedResponse
	 * @return
	 */
	public static Object parseServiceResponse(String reader, Class<?> type) {
		Object returnObj = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			returnObj = mapper.readValue(reader, type);
		} catch (JsonParseException errorException) {
			if (BuildConfig.DEBUG) {
				Log.e("JSON PARSE ERROR", errorException.getMessage());
				Log.e("JSON PARSE ERROR", reader);
			}
			return null;
		} catch (JsonMappingException ioexc) {

			if (BuildConfig.DEBUG) {
				Log.e("JSON MAPPING ERROR", ioexc.getMessage());
				Log.e("JSON MAPPING ERROR", reader);
			}
			return null;
		} catch (IOException e) {

			if (BuildConfig.DEBUG) {
				Log.e("IO ERROR", e.getMessage());
			}
			return null;
		}
		return returnObj;
	}

}
