using System;
using System.Net;
using Xamarin.Essentials;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Xamarin_App
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class WeatherPage : ContentPage
    {
        public WeatherPage()
        {
            InitializeComponent();
        }

        protected async void ShowWeather_OnClicked(object sender, EventArgs e)
        {
            var location = await Geolocation.GetLastKnownLocationAsync();

            using (WebClient client = new WebClient())
            {
                var htmlSource = new HtmlWebViewSource
                {
                    Html = client.DownloadString(
                        $"https://api.openweathermap.org/data/2.5/weather?lat={location.Latitude}&lon={location.Longitude}&mode=html&appid=4526d487f12ef78b82b7a7d113faea64")
                };
                var geoWebView = this.FindByName<WebView>("GeoWebView");
                geoWebView.Source = htmlSource;
            }
        }
    }
}