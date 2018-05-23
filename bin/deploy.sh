#!/usr/bin/env bash

#目前只支持SCP方式
METHOD=SCP

SERVER=60.175.208.233
PORT=222
DEPLOY_USER="iot"
DEPLOY_DIR="~"

PROJECT=$1
VERSION=$2
APP_HOME=$(cd "`dirname $0`/.." && pwd)

LOCAL="$APP_HOME/$PROJECT/target/$PROJECT-$VERSION"
REMOTE="$DEPLOY_DIR/$PROJECT-$VERSION"

RSYNC_OPS="-avz --delete"


if [ "$3"x != ""x ]; then
    LOCAL="$LOCAL/$3"
    REMOTE="$REMOTE/$3"
    RSYNC_OPS=""
fi

deployByScp() {
    echo "$RSYNC_OPS"
    echo "Deploying $PROJECT ..."
    rsync -e "ssh -p $PORT" $RSYNC_OPS "$LOCAL/" "$DEPLOY_USER@$SERVER:$REMOTE"
    if [ $? -eq 0 ]; then
        echo "$PROJECT deployed"
    else
        echo "$PROJECT deploy failed"
    fi
}

case "$METHOD" in
"SCP")
  deployByScp ;;
esac
