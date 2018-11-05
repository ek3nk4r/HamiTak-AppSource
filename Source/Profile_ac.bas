B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=7.3
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals

End Sub

Sub Globals
	Dim url As PhoneIntents
	Private scrollveiw_home As ScrollView
	Private btn_call As Button
	Private btn_channel As Button
	Private btn_insta As Button
	Private btn_site As Button
	Private btn_telegram As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Profile")
	scrollveiw_home.Panel.LoadLayout("Profile_ScrollView")
	scrollveiw_home.Panel.Height = btn_insta.Top + btn_insta.Height + 7dip
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	
End Sub

Sub Label1_Click
	StartActivity(Home)
End Sub

Sub btn_telegram_Click
	StartActivity(url.OpenBrowser("https://t.me/hamitak.ir"))
End Sub

Sub btn_site_Click
	ToastMessageShow("سایت حامی تک در دست ساخت میباشد",False)
End Sub

Sub btn_insta_Click
	Try
		Private i As Intent
		i.Initialize(i.ACTION_VIEW, "instagram://user?username="&"hamitak.ir")
		StartActivity(i)
	Catch
		StartActivity(url.OpenBrowser("https://instagram.com/hamitak.ir"))
	End Try
End Sub

Sub btn_channel_Click
	StartActivity(url.OpenBrowser("https://t.me/hamitak.ir"))
End Sub

Sub btn_call_Click
	Dim i As Intent
	i.Initialize(i.ACTION_VIEW, "tel:04446333610")
	StartActivity(i)
End Sub

Sub btn_home_Click
	StartActivity(Home)
End Sub

Sub btn_profile_Click
	StartActivity(Sefareshat_ac)
End Sub

Sub btm_service_Click
	StartActivity(Service_ac)
End Sub