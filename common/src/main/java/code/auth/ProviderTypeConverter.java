package code.auth;

import code.CodeConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProviderTypeConverter extends CodeConverter<ProviderType> {

    public ProviderTypeConverter() {
        super(ProviderType.class);
    }
}
