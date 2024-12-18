package mmswflow.chessandroidgame.ui_components

sealed class UISizingValue(val value: Int) {

    data object MediumActionButtonWidth : UISizingValue(200)
    data object MediumActionButtonBoxPadding: UISizingValue(6)
    data object MediumActionButtonBorderWidth: UISizingValue(2)
    data object MediumActionButtonTextFontSize: UISizingValue(16)
    data object MediumActionButtonPadding: UISizingValue(2)
    data object MediumActionButtonRoundedCornerShapeSize: UISizingValue(16)

    data object HeaderFontSize: UISizingValue(42)
    data object HeaderPaddingSize: UISizingValue(64)

    data object SmallInfoTextFontSize: UISizingValue(16)
    data object SmallInfoTextPadding: UISizingValue(8)

    data object MediumInfoTextFontSize: UISizingValue(24)
    data object MediumInfoTextPadding : UISizingValue(32)

    data object LargeInfoTextFontSize : UISizingValue(32)
    data object LargeInfoTextPadding : UISizingValue(42)

    data object ScreenTitleTextFontSize : UISizingValue(24)
    data object ScreenTitleTextBottomPadding : UISizingValue(30)
    data object SelectableCardPadding : UISizingValue(16)
    data object SelectableCardRoundedCornerShapeSize: UISizingValue(16)
    data object SelectableCardBorderWidth : UISizingValue(2)
    data object SelectableCardElevation: UISizingValue(4)
    data object SelectableCardMinHeight: UISizingValue(350)
    data object SelectableCardMaxHeight : UISizingValue(450)

    data object SelectionIconIdentifierPadding : UISizingValue(24)

    data object ScreenTopBarBorderWidth : UISizingValue(5)

    data object CarouselPreferredItemWidth: UISizingValue(260)
    data object CarouselItemSpacing: UISizingValue(30)
    data object CarouselItemMinWidth: UISizingValue(35)
    data object CarouselItemMaxWidth: UISizingValue(100)
    data object CarouselSelectableCardHeight: UISizingValue(380)

    data object ChessBoardCellSize: UISizingValue(40)
    data object ChessPieceIconSize: UISizingValue(32)

    data object SelectableBoardCellBorderStrokeWidth: UISizingValue(1)

    data object PlayerInfoCardPadding: UISizingValue(16)
    data object PlayerInfoCardBorderStrokeWidth: UISizingValue(2)
    data object PlayerInfoCardRoundedCornerShapeSize: UISizingValue(16)
    data object PlayerInfoCardIconSize: UISizingValue(128)
    data object PlayerInfoCardIconPadding: UISizingValue(8)


    data object TimerSize: UISizingValue(64)
    data object TimerIndicatorStrokeWidth: UISizingValue(4)
    data object TimerTextFontSize : UISizingValue(16)

    data object GameEndDialogBorderStrokeWidth: UISizingValue(2)
    data object GameEndDialogRoundedCornerSize: UISizingValue(16)
    data object GameEndDialogIconSize: UISizingValue(32)
    data object GameEndDialogIconPadding: UISizingValue(4)
}