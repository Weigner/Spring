package br.com.fiap.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.fiap.model.LojaModel;
import br.com.fiap.exception.ResponseBusinessException;

@Component
public class LojaBusiness {

	@Value("${rest.exception.business.invalid-url}")
	private String invalidUrl;

	public LojaModel applyBusiness(LojaModel loja) throws ResponseBusinessException {

		String url = changeUrltoLowerCase(loja.getUrl());
		loja.setUrl(url);

		verifyUrl(loja.getUrl());

		return loja;
	}

	private String changeUrltoLowerCase(String url) {
		return url.toLowerCase();
	}

	private void verifyUrl(String url) throws ResponseBusinessException {

		if (!url.contains("http://www") && !url.contains("https://www")) {
			throw new ResponseBusinessException(invalidUrl);

		}
	}
}
