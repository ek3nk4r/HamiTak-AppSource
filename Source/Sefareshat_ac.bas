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
	
	Private scrollveiw_home As ScrollView
	Private ImageView4 As ImageView
	Private Label11 As Label
	Private Label50 As Label
	Private Label60 As Label
	Private lbl_peygiri As Label
	Private Panel4 As Panel
	Dim cs As Cursor
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Sefareshat_page")
	cs = Main.sql1.ExecQuery("SELECT * FROM sefareshat ORDER BY id")
	Dim top As Int = 20
	For i=0 To cs.RowCount -1
		cs.Position=i
		Dim pnl As Panel
		pnl.Initialize("pnl")
		pnl.LoadLayout("Sefareshat_scrollView")
		pnl.Tag = i
		scrollveiw_home.Panel.AddView(pnl,Panel4.Left+5dip,top,Panel4.Width,Panel4.Height+3dip)
		top = top + Panel4.Height + 10dip
		If pnl.tag = i Then
			Label11.Text = cs.GetString("servicename")
			Label50.Text = Label11.Text
			Label60.Text = cs.GetString("price")
			ImageView4.Bitmap = LoadBitmap(File.DirAssets,cs.GetString("image"))
			lbl_peygiri.Text = "کد پیگیری سفارش : " & cs.GetString("codepaygiri")
		End If
		scrollveiw_home.Panel.Height = top + 3dip
	Next
	cs.Close
	

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish
End Sub


Sub Label11_Click
	
End Sub

Sub btn_other_Click
	StartActivity(Profile_ac)
End Sub

Sub btn_home_Click
	StartActivity(Home)
End Sub

Sub btm_service_Click
	StartActivity(Service_ac)
End Sub