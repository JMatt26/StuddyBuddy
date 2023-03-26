import * as Device from 'expo-device';
import * as Notifications from 'expo-notifications';

// import request_ressource from './fetchUtil';
import Logger from './Logger/Logger';

export async function sendNotification(expoToken , object = {
  title :'Original Title',
  body : 'And here is the body!',
  data : { data: 'goes here' },
  _displayInForeground :true,
}){
    const message = {
        to: expoToken,
        sound: 'default',
        title: object.title,
        body: object.body,
        data: object.data,
        _displayInForeground: object._displayInForeground,
    };

    await fetch('https://exp.host/--/api/v2/push/send', {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Accept-encoding': 'gzip, deflate',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(message),
    });   
}

export async function registerForPushNotificationsAsync() {
    let token;

    Logger.info(`[Notifications] registerForPushNotificationsAsync`);

    if (Platform.OS === 'android') {
      await Notifications.setNotificationChannelAsync('default', {
        name: 'default',
        importance: Notifications.AndroidImportance.MAX,
        vibrationPattern: [0, 250, 250, 250],
        lightColor: '#FF231F7C',
      });
    }

    if (Device.isDevice) {
      const { status: existingStatus } = await Notifications.getPermissionsAsync();
      let finalStatus = existingStatus;
      if (existingStatus !== 'granted') {
        const { status } = await Notifications.requestPermissionsAsync();
        finalStatus = status;
      }
      if (finalStatus !== 'granted') {
        Logger.error(`[Notifications] Failed to get push token for push notification!`);
        alert('Failed to get push token for push notification!');
        return;
      }
      token = (await Notifications.getExpoPushTokenAsync()).data;
      Logger.info(`[Notifications] Your push token: ${token}`);

    } else {
      //alert('Must use physical device for Push Notifications');
      Logger.error(`[Notifications] Must use physical device for Push Notifications`);
    }
  
    return token;
  }


  export const notificationsObjects = {
    newAppointement: {
      title: "New Appointement",
    }
  };