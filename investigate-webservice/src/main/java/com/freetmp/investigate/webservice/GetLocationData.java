package com.freetmp.investigate.webservice;

import com.uradiosys.www.ServiceApiCallbackHandler;
import com.uradiosys.www.ServiceApiStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;

/**
 * Created by LiuPin on 2015/3/26.
 */
public class GetLocationData {

    private final static Logger LOGGER = LoggerFactory.getLogger(GetLocationData.class);

    private static final ServiceApiCallbackHandler serviceApiCallbackHandler = new ServiceApiCallbackHandler() {


        @Override
        public void receiveResultgetTagStatusListAndSummaryAllEvents(ServiceApiStub.GetTagStatusListAndSummaryAllEventsResponse result) {
            try {
                ServiceApiStub.GetTagStatusResult[] statuses = result.getGetTagStatusListAndSummaryAllEventsResult().getGetTagStatusResult();
                if(statuses != null) {
                    LOGGER.trace("{} tags returned from web service.", statuses.length);
                    for (ServiceApiStub.GetTagStatusResult status : statuses) {
                        LOGGER.info("Get positioning result [map_id={}, x={}, y={}, mac={}]", status.getMapId(), status.getX(), status.getY(), status.getTagMac());
                    }
                } else {
                    LOGGER.trace("Nothing returned from web service.");
                }
            } finally {
            }
        }

        /**
         * auto generated Axis2 Error handler
         * override this method for handling error response from getTagStatusListAndSummaryAllEventswithUserRight operation
         *
         * @param e
         */
        @Override
        public void receiveErrorgetTagStatusListAndSummaryAllEventswithUserRight(Exception e) {
            LOGGER.error("Error occurred when query from remote.", e);
        }
    };

    public static void main(String[] args) throws RemoteException, InterruptedException {
        ServiceApiStub apiStub = new ServiceApiStub();

        ServiceApiStub.GetTagStatusListAndSummaryAllEvents query = new ServiceApiStub.GetTagStatusListAndSummaryAllEvents();
        query.setTagNameKeyword("");
        int[] group = new int[2];//分组信息
        group[0]=1;
        group[1] = 2;
        ServiceApiStub.ArrayOfInt groupArray = new ServiceApiStub.ArrayOfInt();
        groupArray.set_int(group);
        ServiceApiStub.ArrayOfInt viewmapId = new ServiceApiStub.ArrayOfInt();
        viewmapId.set_int(new int[1]);
        query.setViewmapId(viewmapId);
        ServiceApiStub.ArrayOfBoolean events = new ServiceApiStub.ArrayOfBoolean();
        events.set_boolean(new boolean[7]);
        query.setEventArray(events);
        query.setFetchCount(Integer.MAX_VALUE);
        query.setTagGroupIdArray(groupArray);
        query.setSortField("");
        query.setLocatingOnly(true);

        while (true){
            apiStub.startgetTagStatusListAndSummaryAllEvents(query, serviceApiCallbackHandler);
            Thread.sleep(1000);
        }
    }
}
