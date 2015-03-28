
/**
 * ServiceApiCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.uradiosys.www;

    /**
     *  ServiceApiCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ServiceApiCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ServiceApiCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ServiceApiCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getTagStatus method
            * override this method for handling normal response from getTagStatus operation
            */
           public void receiveResultgetTagStatus(
                    com.uradiosys.www.ServiceApiStub.GetTagStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTagStatus operation
           */
            public void receiveErrorgetTagStatus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for hostTagAddByOtherSys method
            * override this method for handling normal response from hostTagAddByOtherSys operation
            */
           public void receiveResulthostTagAddByOtherSys(
                    com.uradiosys.www.ServiceApiStub.HostTagAddByOtherSysResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from hostTagAddByOtherSys operation
           */
            public void receiveErrorhostTagAddByOtherSys(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getLatestAlertInfo method
            * override this method for handling normal response from getLatestAlertInfo operation
            */
           public void receiveResultgetLatestAlertInfo(
                    com.uradiosys.www.ServiceApiStub.GetLatestAlertInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getLatestAlertInfo operation
           */
            public void receiveErrorgetLatestAlertInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for downloadRouteData method
            * override this method for handling normal response from downloadRouteData operation
            */
           public void receiveResultdownloadRouteData(
                    com.uradiosys.www.ServiceApiStub.DownloadRouteDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from downloadRouteData operation
           */
            public void receiveErrordownloadRouteData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getConnectionString method
            * override this method for handling normal response from getConnectionString operation
            */
           public void receiveResultgetConnectionString(
                    com.uradiosys.www.ServiceApiStub.GetConnectionStringResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getConnectionString operation
           */
            public void receiveErrorgetConnectionString(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getWarningRulesByAreaId method
            * override this method for handling normal response from getWarningRulesByAreaId operation
            */
           public void receiveResultgetWarningRulesByAreaId(
                    com.uradiosys.www.ServiceApiStub.GetWarningRulesByAreaIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getWarningRulesByAreaId operation
           */
            public void receiveErrorgetWarningRulesByAreaId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for clearTagAllStatus method
            * override this method for handling normal response from clearTagAllStatus operation
            */
           public void receiveResultclearTagAllStatus(
                    com.uradiosys.www.ServiceApiStub.ClearTagAllStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from clearTagAllStatus operation
           */
            public void receiveErrorclearTagAllStatus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for selectTagStatus method
            * override this method for handling normal response from selectTagStatus operation
            */
           public void receiveResultselectTagStatus(
                    com.uradiosys.www.ServiceApiStub.SelectTagStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from selectTagStatus operation
           */
            public void receiveErrorselectTagStatus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for clearTagStatus method
            * override this method for handling normal response from clearTagStatus operation
            */
           public void receiveResultclearTagStatus(
                    com.uradiosys.www.ServiceApiStub.ClearTagStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from clearTagStatus operation
           */
            public void receiveErrorclearTagStatus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for selectAPStatusList method
            * override this method for handling normal response from selectAPStatusList operation
            */
           public void receiveResultselectAPStatusList(
                    com.uradiosys.www.ServiceApiStub.SelectAPStatusListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from selectAPStatusList operation
           */
            public void receiveErrorselectAPStatusList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteWarningRuleCoverage method
            * override this method for handling normal response from deleteWarningRuleCoverage operation
            */
           public void receiveResultdeleteWarningRuleCoverage(
                    com.uradiosys.www.ServiceApiStub.DeleteWarningRuleCoverageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteWarningRuleCoverage operation
           */
            public void receiveErrordeleteWarningRuleCoverage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMapInfo method
            * override this method for handling normal response from getMapInfo operation
            */
           public void receiveResultgetMapInfo(
                    com.uradiosys.www.ServiceApiStub.GetMapInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMapInfo operation
           */
            public void receiveErrorgetMapInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for selectTagStatusList method
            * override this method for handling normal response from selectTagStatusList operation
            */
           public void receiveResultselectTagStatusList(
                    com.uradiosys.www.ServiceApiStub.SelectTagStatusListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from selectTagStatusList operation
           */
            public void receiveErrorselectTagStatusList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTagOnReception method
            * override this method for handling normal response from getTagOnReception operation
            */
           public void receiveResultgetTagOnReception(
                    com.uradiosys.www.ServiceApiStub.GetTagOnReceptionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTagOnReception operation
           */
            public void receiveErrorgetTagOnReception(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for exchangeTagMac method
            * override this method for handling normal response from exchangeTagMac operation
            */
           public void receiveResultexchangeTagMac(
                    com.uradiosys.www.ServiceApiStub.ExchangeTagMacResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from exchangeTagMac operation
           */
            public void receiveErrorexchangeTagMac(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateHostTagView method
            * override this method for handling normal response from updateHostTagView operation
            */
           public void receiveResultupdateHostTagView(
                    com.uradiosys.www.ServiceApiStub.UpdateHostTagViewResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateHostTagView operation
           */
            public void receiveErrorupdateHostTagView(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getFacilityList method
            * override this method for handling normal response from getFacilityList operation
            */
           public void receiveResultgetFacilityList(
                    com.uradiosys.www.ServiceApiStub.GetFacilityListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getFacilityList operation
           */
            public void receiveErrorgetFacilityList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setSiteSurveyDataToServer method
            * override this method for handling normal response from setSiteSurveyDataToServer operation
            */
           public void receiveResultsetSiteSurveyDataToServer(
                    com.uradiosys.www.ServiceApiStub.SetSiteSurveyDataToServerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setSiteSurveyDataToServer operation
           */
            public void receiveErrorsetSiteSurveyDataToServer(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTagStatusListAndSummaryAllEvents method
            * override this method for handling normal response from getTagStatusListAndSummaryAllEvents operation
            */
           public void receiveResultgetTagStatusListAndSummaryAllEvents(
                    com.uradiosys.www.ServiceApiStub.GetTagStatusListAndSummaryAllEventsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTagStatusListAndSummaryAllEvents operation
           */
            public void receiveErrorgetTagStatusListAndSummaryAllEvents(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMapInfoByFacilityId method
            * override this method for handling normal response from getMapInfoByFacilityId operation
            */
           public void receiveResultgetMapInfoByFacilityId(
                    com.uradiosys.www.ServiceApiStub.GetMapInfoByFacilityIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMapInfoByFacilityId operation
           */
            public void receiveErrorgetMapInfoByFacilityId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPositionPointListByMapId method
            * override this method for handling normal response from getPositionPointListByMapId operation
            */
           public void receiveResultgetPositionPointListByMapId(
                    com.uradiosys.www.ServiceApiStub.GetPositionPointListByMapIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPositionPointListByMapId operation
           */
            public void receiveErrorgetPositionPointListByMapId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateHostGroup method
            * override this method for handling normal response from updateHostGroup operation
            */
           public void receiveResultupdateHostGroup(
                    com.uradiosys.www.ServiceApiStub.UpdateHostGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateHostGroup operation
           */
            public void receiveErrorupdateHostGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for hostTagAddOrUpdate method
            * override this method for handling normal response from hostTagAddOrUpdate operation
            */
           public void receiveResulthostTagAddOrUpdate(
                    com.uradiosys.www.ServiceApiStub.HostTagAddOrUpdateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from hostTagAddOrUpdate operation
           */
            public void receiveErrorhostTagAddOrUpdate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSurveyData method
            * override this method for handling normal response from getSurveyData operation
            */
           public void receiveResultgetSurveyData(
                    com.uradiosys.www.ServiceApiStub.GetSurveyDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSurveyData operation
           */
            public void receiveErrorgetSurveyData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTagCountByCoordinates method
            * override this method for handling normal response from getTagCountByCoordinates operation
            */
           public void receiveResultgetTagCountByCoordinates(
                    com.uradiosys.www.ServiceApiStub.GetTagCountByCoordinatesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTagCountByCoordinates operation
           */
            public void receiveErrorgetTagCountByCoordinates(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for selectTagCountByMapID method
            * override this method for handling normal response from selectTagCountByMapID operation
            */
           public void receiveResultselectTagCountByMapID(
                    com.uradiosys.www.ServiceApiStub.SelectTagCountByMapIDResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from selectTagCountByMapID operation
           */
            public void receiveErrorselectTagCountByMapID(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for selectAPStatus method
            * override this method for handling normal response from selectAPStatus operation
            */
           public void receiveResultselectAPStatus(
                    com.uradiosys.www.ServiceApiStub.SelectAPStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from selectAPStatus operation
           */
            public void receiveErrorselectAPStatus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTagStatusByIP method
            * override this method for handling normal response from getTagStatusByIP operation
            */
           public void receiveResultgetTagStatusByIP(
                    com.uradiosys.www.ServiceApiStub.GetTagStatusByIPResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTagStatusByIP operation
           */
            public void receiveErrorgetTagStatusByIP(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertWarningRule method
            * override this method for handling normal response from insertWarningRule operation
            */
           public void receiveResultinsertWarningRule(
                    com.uradiosys.www.ServiceApiStub.InsertWarningRuleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertWarningRule operation
           */
            public void receiveErrorinsertWarningRule(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMapInfoByMapId method
            * override this method for handling normal response from getMapInfoByMapId operation
            */
           public void receiveResultgetMapInfoByMapId(
                    com.uradiosys.www.ServiceApiStub.GetMapInfoByMapIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMapInfoByMapId operation
           */
            public void receiveErrorgetMapInfoByMapId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for downloadMapImage method
            * override this method for handling normal response from downloadMapImage operation
            */
           public void receiveResultdownloadMapImage(
                    com.uradiosys.www.ServiceApiStub.DownloadMapImageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from downloadMapImage operation
           */
            public void receiveErrordownloadMapImage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getModelATagList method
            * override this method for handling normal response from getModelATagList operation
            */
           public void receiveResultgetModelATagList(
                    com.uradiosys.www.ServiceApiStub.GetModelATagListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getModelATagList operation
           */
            public void receiveErrorgetModelATagList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteStatusByalertId method
            * override this method for handling normal response from deleteStatusByalertId operation
            */
           public void receiveResultdeleteStatusByalertId(
                    com.uradiosys.www.ServiceApiStub.DeleteStatusByalertIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteStatusByalertId operation
           */
            public void receiveErrordeleteStatusByalertId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for downloadSurveyList method
            * override this method for handling normal response from downloadSurveyList operation
            */
           public void receiveResultdownloadSurveyList(
                    com.uradiosys.www.ServiceApiStub.DownloadSurveyListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from downloadSurveyList operation
           */
            public void receiveErrordownloadSurveyList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for activateTagEvent method
            * override this method for handling normal response from activateTagEvent operation
            */
           public void receiveResultactivateTagEvent(
                    com.uradiosys.www.ServiceApiStub.ActivateTagEventResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from activateTagEvent operation
           */
            public void receiveErroractivateTagEvent(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for uploadMapData method
            * override this method for handling normal response from uploadMapData operation
            */
           public void receiveResultuploadMapData(
                    com.uradiosys.www.ServiceApiStub.UploadMapDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from uploadMapData operation
           */
            public void receiveErroruploadMapData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for selectTagStatusListAndSummary method
            * override this method for handling normal response from selectTagStatusListAndSummary operation
            */
           public void receiveResultselectTagStatusListAndSummary(
                    com.uradiosys.www.ServiceApiStub.SelectTagStatusListAndSummaryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from selectTagStatusListAndSummary operation
           */
            public void receiveErrorselectTagStatusListAndSummary(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for testJson method
            * override this method for handling normal response from testJson operation
            */
           public void receiveResulttestJson(
                    com.uradiosys.www.ServiceApiStub.TestJsonResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from testJson operation
           */
            public void receiveErrortestJson(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMapAreaListByMapId method
            * override this method for handling normal response from getMapAreaListByMapId operation
            */
           public void receiveResultgetMapAreaListByMapId(
                    com.uradiosys.www.ServiceApiStub.GetMapAreaListByMapIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMapAreaListByMapId operation
           */
            public void receiveErrorgetMapAreaListByMapId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteWarningRule method
            * override this method for handling normal response from deleteWarningRule operation
            */
           public void receiveResultdeleteWarningRule(
                    com.uradiosys.www.ServiceApiStub.DeleteWarningRuleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteWarningRule operation
           */
            public void receiveErrordeleteWarningRule(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changePosition method
            * override this method for handling normal response from changePosition operation
            */
           public void receiveResultchangePosition(
                    com.uradiosys.www.ServiceApiStub.ChangePositionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changePosition operation
           */
            public void receiveErrorchangePosition(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for reloadTagHost method
            * override this method for handling normal response from reloadTagHost operation
            */
           public void receiveResultreloadTagHost(
                    com.uradiosys.www.ServiceApiStub.ReloadTagHostResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from reloadTagHost operation
           */
            public void receiveErrorreloadTagHost(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTagStatusListAndSummaryAllEventswithUserRight method
            * override this method for handling normal response from getTagStatusListAndSummaryAllEventswithUserRight operation
            */
           public void receiveResultgetTagStatusListAndSummaryAllEventswithUserRight(
                    com.uradiosys.www.ServiceApiStub.GetTagStatusListAndSummaryAllEventswithUserRightResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTagStatusListAndSummaryAllEventswithUserRight operation
           */
            public void receiveErrorgetTagStatusListAndSummaryAllEventswithUserRight(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for startStopLocating method
            * override this method for handling normal response from startStopLocating operation
            */
           public void receiveResultstartStopLocating(
                    com.uradiosys.www.ServiceApiStub.StartStopLocatingResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from startStopLocating operation
           */
            public void receiveErrorstartStopLocating(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for downloadSurveyData method
            * override this method for handling normal response from downloadSurveyData operation
            */
           public void receiveResultdownloadSurveyData(
                    com.uradiosys.www.ServiceApiStub.DownloadSurveyDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from downloadSurveyData operation
           */
            public void receiveErrordownloadSurveyData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setWarningRuleCoverage method
            * override this method for handling normal response from setWarningRuleCoverage operation
            */
           public void receiveResultsetWarningRuleCoverage(
                    com.uradiosys.www.ServiceApiStub.SetWarningRuleCoverageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setWarningRuleCoverage operation
           */
            public void receiveErrorsetWarningRuleCoverage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for uploadPhoneData method
            * override this method for handling normal response from uploadPhoneData operation
            */
           public void receiveResultuploadPhoneData(
                    com.uradiosys.www.ServiceApiStub.UploadPhoneDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from uploadPhoneData operation
           */
            public void receiveErroruploadPhoneData(java.lang.Exception e) {
            }
                


    }
    