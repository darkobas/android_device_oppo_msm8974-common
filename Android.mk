ifneq ($(filter find7 find7op bacon,$(TARGET_DEVICE)),)
    include $(all-subdir-makefiles)

ifeq ($(BOARD_VENDOR),oppo)
ifeq ($(TARGET_BOARD_PLATFORM),msm8974)

include $(call all-makefiles-under,$(LOCAL_PATH))

include $(CLEAR_VARS)

# Create a link for the WCNSS config file, which ends up as a writable
# version in /data/misc/wifi
$(shell mkdir -p $(TARGET_OUT)/etc/firmware/wlan/prima; \
    ln -sf /data/misc/wifi/WCNSS_qcom_cfg.ini \
            $(TARGET_OUT)/etc/firmware/wlan/prima/WCNSS_qcom_cfg.ini)

endif
endif
endif
