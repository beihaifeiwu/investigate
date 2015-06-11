#!/usr/bin/env bash
# Application
APP_NAME="investigate-spring-boot"
USER_BIN_DIR="/usr/bin"

# discover BASEDIR
BASEDIR=`dirname "$0"`/..
BASEDIR=`(cd "$BASEDIR"; pwd)`
ls -l "$0" | grep -e '->' > /dev/null 2>&1
if [ $? = 0 ]; then
  #this is softlink
  _PWD=`pwd`
  _EXEDIR=`dirname "$0"`
  cd "$_EXEDIR"
  _BASENAME=`basename "$0"`
  _REALFILE=`ls -l "$_BASENAME" | sed 's/.*->\ //g'`
   BASEDIR=`dirname "$_REALFILE"`/..
   BASEDIR=`(cd "$BASEDIR"; pwd)`
   cd "$_PWD"
fi

echo "discover basedir $BASEDIR"

SERVICE_FILE_NAME="${APP_NAME}.service"
SYSTEMD_DIR="/etc/systemd/system"
SERVICE_FILE="${SYSTEMD_DIR}/${SERVICE_FILE_NAME}"

install(){
  echo 'start install...'
  if [  -d ${SYSTEMD_DIR} ]; then
    echo "generating systemd service file..."

    if [ -f ${SERVICE_FILE} ]; then
      echo "[Unit]" > ${SERVICE_FILE}
      _EXISTED="EXISTED"
    else
      ${USER_BIN_DIR}/touch ${SERVICE_FILE}
      echo "[Unit]" >> ${SERVICE_FILE}
      _EXISTED="NEW"
    fi

    echo "Description=${APP_NAME}" >> ${SERVICE_FILE}
    echo "After=network.target" >> ${SERVICE_FILE}
    echo "" >> ${SERVICE_FILE}
    echo "[Service]" >> ${SERVICE_FILE}
    echo "ExecStart=${USER_BIN_DIR}/bash ${BASEDIR}/bin/${APP_NAME} start" >> ${SERVICE_FILE}
    echo "ExecStop=${USER_BIN_DIR}/bash ${BASEDIR}/bin/${APP_NAME} stop" >> ${SERVICE_FILE}
    echo "Type=forking" >> ${SERVICE_FILE}
    echo "" >> ${SERVICE_FILE}
    echo "[Install]" >> ${SERVICE_FILE}
    echo "WantedBy=default.target" >> ${SERVICE_FILE}

    echo "generated service content is : "
    echo "+++++++++++"
    ${USER_BIN_DIR}/cat ${SERVICE_FILE}
    echo "+++++++++++"

    echo "enable background service"
    if [ ${_EXISTED} = "EXISTED" ]; then
      ${USER_BIN_DIR}/systemctl daemon-reload
    fi
    ${USER_BIN_DIR}/systemctl enable ${SERVICE_FILE_NAME}
    echo "install finished"
    echo "type 'systemctl start ${SERVICE_FILE_NAME}' to start service"
  else
    echo "install failed... please use centos 7 or above"
  fi
}

uninstall(){
  echo 'start uninstall...'
  if [  -d ${SYSTEMD_DIR} ]; then

    if [ -f ${SERVICE_FILE} ]; then
      echo "stop system service ${SERVICE_FILE_NAME}"
      ${USER_BIN_DIR}/systemctl stop ${SERVICE_FILE_NAME}

      echo "disable system service ${SERVICE_FILE_NAME}"
      ${USER_BIN_DIR}/systemctl disable ${SERVICE_FILE_NAME}

      echo "remove service file ${SERVICE_FILE_NAME}"
      ${USER_BIN_DIR}/rm -f ${SERVICE_FILE}
    else
      echo "service ${APP_NAME} is not installed"
    fi

  else
    echo "uninstall failed... please use centos 7 or above"
  fi
}

case "$1" in
  'install') install ;;
  'uninstall') uninstall ;;
  *)
    echo "Usage: $0 { install | uninstall }"
    exit 1
    ;;
esac

exit 0